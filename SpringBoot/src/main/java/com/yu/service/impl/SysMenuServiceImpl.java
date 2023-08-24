package com.yu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.common.constant.SystemConstants;
import com.yu.common.enums.MenuTypeEnum;
import com.yu.common.enums.StatusEnum;
import com.yu.mapper.SysMenuMapper;
import com.yu.model.bo.RouteBO;
import com.yu.model.entity.SysMenu;
import com.yu.model.vo.RouteVO;
import com.yu.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author za'y
 * @description 针对表【sys_menu(菜单管理)】的数据库操作Service实现
 * @createDate 2023-08-20 21:51:12
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
        implements SysMenuService {

    @Override
    public Set<String> listRolePerms(Set<String> roles) {
        return this.baseMapper.listRolePerms(roles);
    }

    /**
     * 路由列表
     */
    @Override
    @Cacheable(cacheNames = "system", key = "'routes'")
    public List<RouteVO> listRoutes() {
        List<RouteBO> menuList = this.baseMapper.listRoutes();
        return recurRoutes(SystemConstants.ROOT_NODE_ID, menuList);
    }

    /**
     * 递归生成菜单路由层级列表
     *
     * @param parentId 父级ID
     * @param menuList 菜单列表
     * @return 菜单路由层级列表
     */
    private List<RouteVO> recurRoutes(Long parentId, List<RouteBO> menuList) {
        return CollectionUtil.emptyIfNull(menuList).stream()
                .filter(menu -> menu.getParentId().equals(parentId))
                .map(menu -> {
                    RouteVO routeVO = new RouteVO();
                    MenuTypeEnum menuTypeEnum = menu.getType();
                    if (MenuTypeEnum.MENU.equals(menuTypeEnum)) {
                        String routeName = StringUtils.capitalize(StrUtil.toCamelCase(menu.getPath(), '-')); // 路由 name 需要驼峰，首字母大写
                        routeVO.setName(routeName); //  根据name路由跳转 this.$router.push({name:xxx})
                    }
                    routeVO.setPath(menu.getPath()); // 根据path路由跳转 this.$router.push({path:xxx})
                    routeVO.setRedirect(menu.getRedirect());
                    routeVO.setComponent(menu.getComponent());

                    RouteVO.Meta meta = new RouteVO.Meta();
                    meta.setTitle(menu.getName());
                    meta.setIcon(menu.getIcon());
                    meta.setRoles(menu.getRoles());
                    meta.setHidden(StatusEnum.DISABLE.getValue().equals(menu.getVisible()));
                    meta.setKeepAlive(true);
                    routeVO.setMeta(meta);

                    List<RouteVO> children = recurRoutes(menu.getId(), menuList);
                    routeVO.setChildren(children);
                    return routeVO;
                }).toList();
    }
}





package com.yu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.common.model.Option;
import com.yu.model.entity.SysMenu;
import com.yu.model.form.MenuForm;
import com.yu.model.vo.MenuVO;
import com.yu.model.vo.RouteVO;

import java.util.List;
import java.util.Set;

/**
* @author za'y
* @description 针对表【sys_menu(菜单管理)】的数据库操作Service
* @createDate 2023-08-20 21:51:12
*/
public interface SysMenuService extends IService<SysMenu> {


    Set<String> listRolePerms(Set<String> roles);

    List<RouteVO> listRoutes();

    /**
     * 获取菜单表格列表
     *
     * @return 菜单表格列表
     */
    List<MenuVO> listMenus(String keywords,Integer status);

    /**
     * 新增菜单
     *
     * @param menu
     * @return
     */
    boolean saveMenu(MenuForm menu);
    /**
     * 获取菜单下拉列表
     *
     * @return
     */
    List<Option> listMenuOptions();
}

package com.yu.mapper;

import com.yu.model.bo.RouteBO;
import com.yu.model.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.service.impl.SysMenuServiceImpl;

import java.util.List;
import java.util.Set;

/**
* @author za'y
* @description 针对表【sys_menu(菜单管理)】的数据库操作Mapper
* @createDate 2023-08-20 21:51:12
* @Entity com.yu.model.entity.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    Set<String> listRolePerms(Set<String> roles);

    List<RouteBO> listRoutes();
}





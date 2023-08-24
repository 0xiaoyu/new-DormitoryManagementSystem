package com.yu.service;

import com.yu.model.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
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
}

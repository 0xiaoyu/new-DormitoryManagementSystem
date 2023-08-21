package com.yu.mapper;

import com.yu.model.entry.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Set;

/**
* @author za'y
* @description 针对表【sys_menu(菜单管理)】的数据库操作Mapper
* @createDate 2023-08-20 21:51:12
* @Entity com.yu.model.entry.SysMenu
*/
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    Set<String> listRolePerms(Set<String> roles);
}





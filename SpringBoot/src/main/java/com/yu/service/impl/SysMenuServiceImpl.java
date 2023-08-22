package com.yu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.mapper.SysMenuMapper;
import com.yu.model.entity.SysMenu;
import com.yu.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
* @author za'y
* @description 针对表【sys_menu(菜单管理)】的数据库操作Service实现
* @createDate 2023-08-20 21:51:12
*/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu>
    implements SysMenuService{

    @Override
    public Set<String> listRolePerms(Set<String> roles) {
        return this.baseMapper.listRolePerms(roles);
    }
}





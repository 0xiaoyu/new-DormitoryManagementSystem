package com.yu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.mapper.SysUserMapper;
import com.yu.model.dto.UserAuthInfo;
import com.yu.model.entity.SysUser;
import com.yu.service.SysMenuService;
import com.yu.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
* @author za'y
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-08-20 12:44:54
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Resource
    private SysMenuService menuService;
    @Override
    public boolean saveUser(SysUser user) {
        return false;
    }

    @Override
    public UserAuthInfo getUserAuthInfo(String username) {
        UserAuthInfo userAuthInfo = this.baseMapper.getUserAuthInfo(username);
        if (userAuthInfo != null) {
            Set<String> roles = userAuthInfo.getRoles();
            if (CollectionUtil.isNotEmpty(roles)) {
                Set<String> perms = menuService.listRolePerms(roles);
                userAuthInfo.setPerms(perms);
            }
        }
        return userAuthInfo;
    }
}





package com.yu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.common.constant.UserRoleConstants;
import com.yu.mapper.SysUserMapper;
import com.yu.mapper.SysUserRoleMapper;
import com.yu.model.entity.SysUser;
import com.yu.model.entity.SysUserRole;
import com.yu.service.SysUserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author za'y
 * @description 针对表【sys_user_role(用户和角色关联表)】的数据库操作Service实现
 * @createDate 2023-08-22 23:43:58
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements SysUserRoleService {

    @Resource
    private SysUserMapper sysUserMapper;


}





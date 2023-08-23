package com.yu.service;

import com.yu.model.dto.UserAuthInfo;
import com.yu.model.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统用户服务类
 *
 * @author za'y
 * @since  2023-08-20 12:44:54
*/
public interface SysUserService extends IService<SysUser> {
    /**
     * 新增用户
     * @param user 用户表单对象
     * @return 是否新增成功
     */
    boolean saveUser(SysUser user);

    /**
     * 查询用户认证信息
     *
     * @param usernameOrEmail 用户名
     * @return 用户认证信息
     */
    UserAuthInfo getUserAuthInfo(String usernameOrEmail);
}

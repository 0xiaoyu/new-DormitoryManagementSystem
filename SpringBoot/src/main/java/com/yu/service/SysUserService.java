package com.yu.service;

import cn.hutool.system.UserInfo;
import com.yu.model.dto.UserAuthInfo;
import com.yu.model.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.vo.UserInfoVO;

/**
 * 系统用户服务类
 *
 * @author za'y
 * @since  2023-08-20 12:44:54
*/
public interface SysUserService extends IService<SysUser> {

    /**
     * 查询用户认证信息
     *
     * @param usernameOrEmail 用户名
     * @return 用户认证信息
     */
    UserAuthInfo getUserAuthInfo(String usernameOrEmail);

    /**
     * 获取用户详细信息
     */
    UserInfoVO getUserLoginInfo();
}

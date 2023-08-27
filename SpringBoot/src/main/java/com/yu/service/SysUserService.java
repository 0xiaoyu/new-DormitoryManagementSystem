package com.yu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.dto.UserAuthInfo;
import com.yu.model.entity.SysUser;
import com.yu.model.query.UserPageQuery;
import com.yu.model.vo.UserInfoVO;
import com.yu.model.vo.UserPageVO;

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

    IPage<UserPageVO> getUserPage(UserPageQuery queryParams);
}

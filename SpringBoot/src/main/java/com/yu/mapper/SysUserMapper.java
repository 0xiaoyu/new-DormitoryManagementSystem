package com.yu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.dto.UserAuthInfo;
import com.yu.model.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.model.query.UserPageQuery;
import com.yu.model.vo.UserPageVO;

/**
* @author za'y
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2023-08-20 12:44:54
* @Entity com.yu.model.entry.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    UserAuthInfo getUserAuthInfo(String username,String email);

    IPage<UserPageVO> getUserPage(Page<UserPageVO> page, UserPageQuery queryParams);
}





package com.yu.mapper;

import com.yu.model.dto.UserAuthInfo;
import com.yu.model.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author za'y
* @description 针对表【sys_user】的数据库操作Mapper
* @createDate 2023-08-20 12:44:54
* @Entity com.yu.model.entry.SysUser
*/
public interface SysUserMapper extends BaseMapper<SysUser> {

    UserAuthInfo getUserAuthInfo(String username,String email);
}





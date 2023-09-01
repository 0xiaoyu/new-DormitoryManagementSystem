package com.yu.service.impl;


import org.springframework.stereotype.Service;
import com.yu.service.IUserService;
import com.yu.model.entity.UserEntity;
import com.yu.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 人员表 服务层实现。
 *
 * @author yu
 * @since 2.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

}
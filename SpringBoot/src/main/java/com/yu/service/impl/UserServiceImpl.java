package com.yu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.mapper.UserMapper;
import com.yu.model.entity.UserEntity;
import com.yu.model.query.TbUserPageQuery;
import com.yu.model.vo.DormitoryPageVo;
import com.yu.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 人员表 服务层实现。
 *
 * @author yu
 * @since 2.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Override
    public Page<DormitoryPageVo> pageDormitory(TbUserPageQuery page) {
        LambdaQueryWrapper<UserEntity> lq = Wrappers.<UserEntity>lambdaQuery()
                .like(StringUtils.hasText(page.getName()), UserEntity::getName, page.getName())
                .eq(!Objects.isNull(page.getTypeId()), UserEntity::getRole, page.getTypeId())
                .eq(UserEntity::getDeleted, 0);
        return baseMapper.pageDormitory(page.getPage(), lq);
    }

    @Override
    public Page<DormitoryPageVo> pageRepair(TbUserPageQuery page) {
        LambdaQueryWrapper<UserEntity> lq = Wrappers.<UserEntity>lambdaQuery()
                .like(StringUtils.hasText(page.getName()), UserEntity::getName, page.getName())
                .eq(!Objects.isNull(page.getTypeId()), UserEntity::getRole, page.getTypeId())
                .eq(UserEntity::getDeleted, 0);
        return baseMapper.pageRepair(page.getPage(), lq);
    }
}
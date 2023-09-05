package com.yu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.common.enums.UsetTypeEnum;
import com.yu.mapper.UserMapper;
import com.yu.model.entity.UserEntity;
import com.yu.model.query.TbUserPageQuery;
import com.yu.model.vo.TbUserPageVo;
import com.yu.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 人员表 服务层实现。
 *
 * @author yu
 * @since 2.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Override
    public Page<TbUserPageVo> pageDormitory(TbUserPageQuery page) {
        LambdaQueryWrapper<UserEntity> lq = Wrappers.<UserEntity>lambdaQuery()
                .like(StringUtils.hasText(page.getName()), UserEntity::getName, page.getName())
                .eq(UserEntity::getRole, UsetTypeEnum.DORMITORY)
                .eq(UserEntity::getDeleted, 0);
        return baseMapper.pageDormitory(page.getPage(), lq);
    }

    @Override
    public Page<TbUserPageVo> pageRepair(TbUserPageQuery page) {
        LambdaQueryWrapper<UserEntity> lq = Wrappers.<UserEntity>lambdaQuery()
                .like(StringUtils.hasText(page.getName()), UserEntity::getName, page.getName())
                .eq(UserEntity::getRole, UsetTypeEnum.REPAIR)
                .eq(UserEntity::getDeleted, 0);
        return baseMapper.pageRepair(page.getPage(), lq);
    }
}
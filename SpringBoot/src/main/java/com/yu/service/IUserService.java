package com.yu.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.query.TbUserPageQuery;
import com.yu.model.vo.TbUserPageVo;

/**
 * 人员表 服务层。
 *
 * @author yu
 * @since 2.0
 */
public interface IUserService extends IService<UserEntity> {

    Page<TbUserPageVo> pageDormitory(TbUserPageQuery page);
    Page<TbUserPageVo> pageRepair(TbUserPageQuery page);
}
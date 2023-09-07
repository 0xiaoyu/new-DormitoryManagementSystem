package com.yu.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.entity.MaintenanceEntity;
import com.yu.model.query.MaintenPageQuery;
import com.yu.model.vo.MaintenPageVo;

/**
 * 维修人员表 服务层。
 *
 * @author yu
 * @since 1.0
 */
public interface MaintenanceService extends IService<MaintenanceEntity> {

    Page<MaintenPageVo> getPageByCondition(MaintenPageQuery query);
}
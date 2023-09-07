package com.yu.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.query.MaintenPageQuery;
import com.yu.model.vo.MaintenPageVo;
import org.springframework.stereotype.Service;
import com.yu.service.MaintenanceService;
import com.yu.model.entity.MaintenanceEntity;
import com.yu.mapper.TbMaintenanceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 维修人员表 服务层实现。
 *
 * @author yu
 * @since 1.0
 */
@Service
public class MaintenanceServiceImpl extends ServiceImpl<TbMaintenanceMapper, MaintenanceEntity> implements MaintenanceService {

    @Override
    public Page<MaintenPageVo> getPageByCondition(MaintenPageQuery query) {
        Page<MaintenPageVo> page = query.getPage();
        return baseMapper.getPageByCondition(page, query);
    }
}
package com.yu.service.impl;


import org.springframework.stereotype.Service;
import com.yu.service.MaintenanceService;
import com.yu.model.entity.TbMaintenanceEntity;
import com.yu.mapper.TbMaintenanceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 维修人员表 服务层实现。
 *
 * @author yu
 * @since 1.0
 */
@Service
public class MaintenanceServiceImpl extends ServiceImpl<TbMaintenanceMapper, TbMaintenanceEntity> implements MaintenanceService {

}
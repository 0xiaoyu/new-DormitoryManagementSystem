package com.yu.service.impl;


import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import com.yu.model.entity.AccessLogEntity;
import com.yu.mapper.AccessLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 进入记录 服务层实现。
 *
 * @author yu
 * @since 1.0
 */
@Service
public class AccessLogServiceImpl extends ServiceImpl<AccessLogMapper, AccessLogEntity> implements IService<AccessLogEntity> {

}
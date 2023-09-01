package com.yu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.mapper.AccessLogMapper;
import com.yu.model.entity.AccessLogEntity;
import com.yu.service.AccessLogService;
import org.springframework.stereotype.Service;

/**
 * 进入记录 服务层实现。
 *
 * @author yu
 * @since 1.0
 */
@Service
public class AccessLogServiceImpl extends ServiceImpl<AccessLogMapper, AccessLogEntity> implements AccessLogService {

}
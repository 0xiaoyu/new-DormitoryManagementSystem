package com.yu.service.impl;


import org.springframework.stereotype.Service;
import com.yu.service.EleLogService;
import com.yu.model.entity.EleLogEntity;
import com.yu.mapper.EleLogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 电费日志 服务层实现。
 *
 * @author yu
 * @since 1.0
 */
@Service
public class EleLogServiceImpl extends ServiceImpl<EleLogMapper, EleLogEntity> implements EleLogService {

}
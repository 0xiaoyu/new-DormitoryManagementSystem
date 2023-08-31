package com.yu.service.impl;


import org.springframework.stereotype.Service;
import com.yu.service.SugTextService;
import com.yu.model.entity.SugTextEntity;
import com.yu.mapper.SugTextMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 建议内容 服务层实现。
 *
 * @author yu
 * @since 1.0
 */
@Service
public class SugTextServiceImpl extends ServiceImpl<SugTextMapper, SugTextEntity> implements SugTextService {

}
package com.yu.service.impl;


import org.springframework.stereotype.Service;
import com.yu.service.SugBoxService;
import com.yu.model.entity.SugBoxEntity;
import com.yu.mapper.SugBoxMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 建议箱，反馈箱 服务层实现。
 *
 * @author yu
 * @since 1.0
 */
@Service
public class SugBoxServiceImpl extends ServiceImpl<SugBoxMapper, SugBoxEntity> implements SugBoxService {

}
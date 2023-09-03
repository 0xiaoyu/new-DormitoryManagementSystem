package com.yu.service.impl;


import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.mapper.AccessLogMapper;
import com.yu.model.entity.AccessLogEntity;
import com.yu.model.query.PassLogPageQuery;
import com.yu.model.vo.PassPageVo;
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

    @Override
    public Page<PassPageVo> getPageQuery(PassLogPageQuery query) {
        Page<PassPageVo> page = query.getPage();
        boolean s = query.getType().contains("0,1");
        boolean o = query.getType().contains("2,3");
        Assert.isTrue(s | o, "type参数错误");
        return baseMapper.getPageQuery(page, s, o,query);
    }
}
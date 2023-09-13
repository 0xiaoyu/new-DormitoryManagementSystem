package com.yu.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.vo.NoticeVo;
import org.springframework.stereotype.Service;
import com.yu.service.ReceiveNoticeMsgService;
import com.yu.model.entity.ReceiveNoticeMsgEntity;
import com.yu.mapper.ReceiveNoticeMsgMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 接收通知 服务层实现。
 *
 * @author yu
 * @since 1.0
 */
@Service
public class ReceiveNoticeMsgServiceImpl extends ServiceImpl<ReceiveNoticeMsgMapper, ReceiveNoticeMsgEntity> implements ReceiveNoticeMsgService {

    @Override
    public Page<NoticeVo> getNoticeList(Page<NoticeVo> page, Long userId) {
        return baseMapper.getNoticeList(page, userId);
    }

    @Override
    public Long getNoReadCount(Long id) {
        return baseMapper.getNoReadCount(id);
    }
}
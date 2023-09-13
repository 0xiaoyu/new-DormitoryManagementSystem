package com.yu.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.entity.ReceiveNoticeMsgEntity;
import com.yu.model.vo.NoticeVo;

/**
 * 接收通知 服务层。
 *
 * @author yu
 * @since 1.0
 */
public interface ReceiveNoticeMsgService extends IService<ReceiveNoticeMsgEntity> {

    Page<NoticeVo> getNoticeList(Page<NoticeVo> page, Long userId);

    Long getNoReadCount(Long id);
}
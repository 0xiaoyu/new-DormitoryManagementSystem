package com.yu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.entity.ReceiveNoticeMsgEntity;
import com.yu.model.vo.NoticeVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 接收通知 映射层。
 *
 * @author yu
 * @since 1.0
 */
@Mapper
public interface ReceiveNoticeMsgMapper extends BaseMapper<ReceiveNoticeMsgEntity> {


    Page<NoticeVo> getNoticeList(Page<NoticeVo> page, Long id);

    Long getNoReadCount(Long id);
}

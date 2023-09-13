package com.yu.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.yu.common.enums.NoticeEnum;
import com.yu.mapper.SenderNoticeMsgMapper;
import com.yu.model.entity.ReceiveNoticeMsgEntity;
import com.yu.model.entity.SenderNoticeMsgEntity;
import com.yu.model.entity.SysUser;
import com.yu.model.vo.NoticeVo;
import com.yu.service.SenderNoticeMsgService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * 通知消息 服务层实现。
 *
 * @author yu
 * @since 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class SenderNoticeMsgServiceImpl extends ServiceImpl<SenderNoticeMsgMapper, SenderNoticeMsgEntity> implements SenderNoticeMsgService {

    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public Boolean sysNotice(String msg,Long id) {
        try {
            SenderNoticeMsgEntity sender = SenderNoticeMsgEntity.builder().senderId(0L).type(NoticeEnum.SYSTEM_NOTICE).msg(msg).build();
            save(sender);
            ReceiveNoticeMsgEntity receive = ReceiveNoticeMsgEntity.builder().noticeId(sender.getId()).receiveId(id).build();
            Db.save(receive);
            SysUser one = Db.lambdaQuery(SysUser.class).select(SysUser::getName).eq(SysUser::getId, id).one();
            // 发送消息
            messagingTemplate.convertAndSendToUser(id.toString(), "/notice/student", new NoticeVo(one.getName(), msg, false, NoticeEnum.SYSTEM_NOTICE));
            return true;
        } catch (Exception e) {
            log.error("发送系统通知失败 {}", e.getMessage());
            return false;
        }
    }
}
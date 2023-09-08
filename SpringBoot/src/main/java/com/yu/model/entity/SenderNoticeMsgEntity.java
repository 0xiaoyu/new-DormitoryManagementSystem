package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yu.common.enums.NoticeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通知消息 实体类。
 *
 * @author yu
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "通知消息")
@TableName(value = "sender_notice_msg")
public class SenderNoticeMsgEntity {

    /**
     * 通知id
     */
    @Schema(description = "通知id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 发送者id，在tb_user表内的id
     */
    @Schema(description = "发送者id，在tb_user表内的id")
    @TableField(value = "sender_id")
    private Long senderId;

    /**
     * 通知内容
     */
    @Schema(description = "通知内容")
    @TableField(value = "n_msg")
    private String nMsg;

    /**
     * 通知类型
     */
    @Schema(description = "通知类型")
    @TableField(value = "n_type")
    private NoticeEnum nType;


}

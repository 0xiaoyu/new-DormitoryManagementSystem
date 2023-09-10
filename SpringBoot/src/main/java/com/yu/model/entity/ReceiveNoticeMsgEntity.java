package com.yu.model.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;

import java.lang.Long;
import java.lang.Integer;

/**
 * 接收通知 实体类。
 *
 * @author yu
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "接收通知")
@TableName(value = "receive_notice_msg")
public class ReceiveNoticeMsgEntity {

    /**
     * 接收通知id
     */
    @Schema(description = "接收通知id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 接收的通知id
     */
    @Schema(description = "接收的通知id")
    @TableField(value = "notice_id")
    private Long noticeId;

    /**
     * 接收者id
     */
    @Schema(description = "接收者id")
    @TableField(value = "receive_id")
    private Long receiveId;

    /**
     * 接收状态,0未读，1已读
     */
    @Schema(description = "接收状态,0未读，1已读")
    @TableField(value = "r_status")
    private Integer rStatus;


}

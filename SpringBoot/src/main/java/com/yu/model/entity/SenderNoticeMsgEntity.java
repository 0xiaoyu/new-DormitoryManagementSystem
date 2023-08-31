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
import java.lang.String;
import java.lang.Integer;

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
    private Integer senderId;

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
    private Integer nType;


}

package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 电费日志 实体类。
 *
 * @author yu
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "电费日志")
@TableName(value = "pay_log")
public class PayLogEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 宿舍id
     */
    @Schema(description = "宿舍id")
    @TableField(value = "dormitory_id")
    private Long dormitoryId;

    /**
     * 缴费金额
     */
    @Schema(description = "缴费金额")
    @TableField(value = "amount")
    private Double amount;

    /**
     * 缴费者id
     */
    @Schema(description = "缴费者id")
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(value = "create_time")
    private Timestamp createTime;

    /**
     * 缴费时间
     */
    @Schema(description = "缴费时间")
    @TableField(value = "update_time")
    private Timestamp updateTime;

    /**
     * 订单状态 0 未支付，1完成
     */
    @Schema(description = "订单状态 0 未支付，1完成")
    @TableField(value = "status")
    private Integer status;

    @Schema(description = "类型")
    @TableField(value = "type")
    private Integer type;


}

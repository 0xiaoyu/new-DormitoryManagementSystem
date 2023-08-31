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
import java.sql.Timestamp;
import java.lang.Boolean;
import java.lang.Integer;

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
@TableName(value = "ele_log")
public class EleLogEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 宿舍id
     */
    @Schema(description = "宿舍id")
    @TableField(value = "dormitory_id")
    private Integer dormitoryId;

    /**
     * 缴费金额
     */
    @Schema(description = "缴费金额")
    @TableField(value = "amount")
    private Integer amount;

    /**
     * 缴费者id
     */
    @Schema(description = "缴费者id")
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 缴费时间
     */
    @Schema(description = "缴费时间")
    @TableField(value = "create_time")
    private Timestamp createTime;

    /**
     * 订单状态 0 未支付，1完成
     */
    @Schema(description = "订单状态 0 未支付，1完成")
    @TableField(value = "status")
    private Boolean status;


}

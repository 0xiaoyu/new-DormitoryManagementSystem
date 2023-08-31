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
import java.lang.Integer;

/**
 * 维修人员表 实体类。
 *
 * @author yu
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "维修人员表")
@TableName(value = "tb_maintenance")
public class TbMaintenanceEntity {

    /**
     * 维修id
     */
    @Schema(description = "维修id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 宿舍号
     */
    @Schema(description = "宿舍号")
    @TableField(value = "dormitory_id")
    private Integer dormitoryId;

    /**
     * 维修详情
     */
    @Schema(description = "维修详情")
    @TableField(value = "detail")
    private Integer detail;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(value = "create_time")
    private Timestamp createTime;

    /**
     * 请求的学生,通过学生获取电话
     */
    @Schema(description = "请求的学生,通过学生获取电话")
    @TableField(value = "student")
    private Integer student;

    /**
     * 维修状态，0待支付，1待维修，2完成，3异常
     */
    @Schema(description = "维修状态，0待支付，1待维修，2完成，3异常")
    @TableField(value = "status")
    private Integer status;

    /**
     * 维修人员id,通过维修人员表获取电话
     */
    @Schema(description = "维修人员id,通过维修人员表获取电话")
    @TableField(value = "maintenance_person_id")
    private Integer maintenancePersonId;

    /**
     * 维修的类型id
     */
    @Schema(description = "维修的类型id")
    @TableField(value = "type_id")
    private Integer typeId;


}

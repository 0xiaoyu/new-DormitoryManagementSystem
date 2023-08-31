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

import java.sql.Timestamp;
import java.lang.Integer;

/**
 * 建议箱，反馈箱 实体类。
 *
 * @author yu
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "建议箱，反馈箱")
@TableName(value = "sug_box")
public class SugBoxEntity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    @Schema(description = "学生id")
    @TableField(value = "student_id")
    private Integer studentId;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(value = "create_time")
    private Timestamp createTime;

    /**
     * 建议状态 0 未处理，1未恢复，2已回复,3已经处理
     */
    @Schema(description = "建议状态 0 未处理，1未恢复，2已回复,3已经处理")
    @TableField(value = "status")
    private Integer status;

    /**
     * 回复人员id
     */
    @Schema(description = "回复人员id")
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 建议类型是否匿名
     */
    @Schema(description = "建议类型是否匿名")
    @TableField(value = "type")
    private Integer type;


}

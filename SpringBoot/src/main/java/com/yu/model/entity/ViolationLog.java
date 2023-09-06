package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yu.common.base.BaseEntity;
import com.yu.common.enums.ViolationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 违规记录
 * @TableName violation_log
 */
@TableName(value ="violation_log")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class ViolationLog extends BaseEntity implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 违规学生id
     */
    @TableField(value = "student_id")
    private Long studentId;

    /**
     * 违规类型
     */
    @TableField(value = "type")
    private ViolationTypeEnum type;

    /**
     * 状态(0-正常；1-申诉；)
     */
    @TableField(value = "flag")
    private Integer flag;

    /**
     * 逻辑删除标识(1:已删除;0:未删除)
     */
    @TableField(value = "deleted")
    private Integer deleted;

    /**
     * 违规详情
     */
    @TableField(value = "detail")
    private String  detail;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
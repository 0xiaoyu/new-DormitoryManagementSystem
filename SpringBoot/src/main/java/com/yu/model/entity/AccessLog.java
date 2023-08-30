package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 进入记录
 * @TableName access_log
 */
@TableName(value ="access_log")
@Data
@EqualsAndHashCode
public class AccessLog implements Serializable {
    /**
     * 进出id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 进出id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 进出类型 0 进 1出
     */
    @TableField(value = "a_type")
    private Integer aType;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
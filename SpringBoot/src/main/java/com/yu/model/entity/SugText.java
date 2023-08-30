package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 建议内容
 * @TableName sug_text
 */
@TableName(value ="sug_text")
@Data
@EqualsAndHashCode
public class SugText implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 建议箱id
     */
    @TableField(value = "s_id")
    private Integer sId;

    /**
     * 内容
     */
    @TableField(value = "s_text")
    private String sText;

    /**
     * 0发起，1回复
     */
    @TableField(value = "type")
    private Integer type;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
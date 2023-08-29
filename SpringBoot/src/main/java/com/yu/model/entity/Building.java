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
 * 宿舍楼栋
 * @TableName d_building
 */
@TableName(value ="d_building")
@Data
@EqualsAndHashCode
public class Building implements Serializable {
    /**
     * 楼栋id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 楼名字,10字以内
     */
    @TableField(value = "build_name")
    private String buildName;

    @TableField(value = "type")
    private Integer type;

    /**
     * 经度
     */
    @TableField(value = "latitude")
    private Double latitude;

    /**
     * 纬度
     */
    @TableField(value = "longitude")
    private Double longitude;

    /**
     * 一层的最大房间号
     */
    @TableField(value = "maxroom")
    private Integer maxroom;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
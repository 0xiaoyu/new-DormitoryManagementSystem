package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 * @TableName tb_dormitory
 */
@TableName(value ="tb_dormitory")
@Data
@EqualsAndHashCode
public class Dormitory implements Serializable {
    /**
     * 宿舍id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 楼层栋
     */
    @TableField(value = "building_id")
    private Long buildingId;

    /**
     * 宿舍号后2位为宿舍号，前为楼层
     */
    @TableField(value = "dormitory_number")
    private Integer dormitoryNumber;

    /**
     * 宿舍容量
     */
    @TableField(value = "capacity")
    private Integer capacity;

    /**
     * 电费
     */
    @TableField(value = "electricity")
    private Double electricity;

    /**
     * 水费
     */
    @TableField(value = "water")
    private Double water;

    /**
     * 电的状态(0-正常；1-停用;2-送水)
     */
    @TableField(value = "e_status")
    private Integer eStatus;

    /**
     * 水状态(0-正常;1-停用;2-送水)
     */
    @TableField(value = "w_status")
    private Integer wStatus;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
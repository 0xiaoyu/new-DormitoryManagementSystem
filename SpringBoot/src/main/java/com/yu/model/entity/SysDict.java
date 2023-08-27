package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 字典数据表
 *
 * @author haoxr
 * @since 2022/12/17
 */
@Data
@TableName(value ="sys_dict")
public class SysDict implements Serializable {

    @TableId(type = IdType.AUTO)
    @Schema(description="字典ID")
    private Long id;

    @Schema(description="类型编码")
    private String typeCode;

    @Schema(description="字典名称")
    private String name;

    @Schema(description="字典值")
    private String value;

    @Schema(description="状态(1:启用;0:禁用)")
    private Integer status;

    @Schema(description="排序")
    private Integer sort;

    @Schema(description = "字典备注")
    private String remark;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
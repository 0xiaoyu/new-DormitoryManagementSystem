package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yu.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 字典类型实体
 *
 * @author zay
 * @since 2023/8/25
 */
@Data
@Schema(description = "字典类型")
@TableName(value ="sys_dict_type")
public class SysDictType extends BaseEntity {

    @Schema(description="字典类型ID")
    @TableId(type = IdType.AUTO)
    private Long id;

    @Schema(description="类型名称")
    private String name;

    @Schema(description="类型编码")
    private String code;

    @Schema(description="类型状态(1:启用;0:禁用)")
    private Integer status;

    @Schema(description = "备注")
    private String remark;
}
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
import java.lang.String;
import java.lang.Integer;

/**
 * 建议内容 实体类。
 *
 * @author yu
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "建议内容")
@TableName(value = "sug_text")
public class SugTextEntity {

    /**
     * id
     */
    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 建议箱id
     */
    @Schema(description = "建议箱id")
    @TableField(value = "s_id")
    private Integer sId;

    /**
     * 内容
     */
    @Schema(description = "内容")
    @TableField(value = "s_text")
    private String sText;

    /**
     * 0发起，1回复
     */
    @Schema(description = "0发起，1回复")
    @TableField(value = "type")
    private Integer type;


}

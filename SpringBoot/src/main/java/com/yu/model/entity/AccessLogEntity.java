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
 * 进入记录 实体类。
 *
 * @author yu
 * @since 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "进入记录")
@TableName(value = "access_log")
public class AccessLogEntity {

    /**
     * 进出id
     */
    @Schema(description = "进出id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 进出id
     */
    @Schema(description = "进出id")
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(value = "create_time")
    private Timestamp createTime;

    /**
     * 进出类型 0 进 1出
     */
    @Schema(description = "进出类型 0 进 1出")
    @TableField(value = "a_type")
    private Integer aType;


}

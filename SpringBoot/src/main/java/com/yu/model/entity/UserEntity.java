package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yu.common.enums.GenderEnum;
import com.yu.common.enums.UsetTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 人员表 实体类。
 *
 * @author yu
 * @since 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "人员表")
@TableName(value = "tb_user")
public class UserEntity {

    /**
     * 人员id
     */
    @Schema(description = "人员id")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 宿管名字
     */
    @Schema(description = "宿管名字")
    @TableField(value = "name")
    private String name;
    /*角色*/
    @TableField(value = "role")
    private Long role;

    /*类型id*/
    @TableField(value = "type_id")
    private UsetTypeEnum typeId;

    /**
     * 性别0 男 1女
     */
    @Schema(description = "性别0 男 1女")
    @TableField(value = "gender")
    private GenderEnum gender;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @TableField(value = "phone")
    private String phone;

    /**
     * 年龄
     */
    @Schema(description = "年龄")
    @TableField(value = "age")
    private Integer age;

    /**
     * 逻辑删除标识(0-未删除；1-已删除)
     */
    @Schema(description = "逻辑删除标识(0-未删除；1-已删除)")
    @TableField(value = "deleted")
    private Integer deleted;


}

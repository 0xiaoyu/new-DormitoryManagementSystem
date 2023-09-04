package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yu.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统表
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_user")
@Data
public class SysUser extends BaseEntity {


    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 密码,最多只能 30 字符
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户状态(1:正常;0:禁用)
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 绑定id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 逻辑删除标志(0:未删除，1:已删除)
     */
    @TableField(value = "deleted")
    private Integer deleted;
}
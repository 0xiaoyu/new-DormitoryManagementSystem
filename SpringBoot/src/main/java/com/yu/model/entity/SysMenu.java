package com.yu.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yu.common.base.BaseEntity;
import com.yu.common.enums.MenuTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单管理
 * @TableName sys_menu
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value ="sys_menu")
@Data
public class SysMenu extends BaseEntity {

    @Schema(description = "菜单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "父菜单ID")
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 父节点ID路径
     */
    @TableField(value = "tree_path")
    private String treePath;

    @Schema(description = "菜单名称")
    @TableField(value = "name")
    private String name;

    @Schema(description = "菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)")
    @TableField(value = "type")
    private MenuTypeEnum type;


    @Schema(description = "路由路径(浏览器地址栏路径)")
    @TableField(value = "path")
    private String path;

    @Schema(description = "组件路径(vue页面完整路径，省略.vue后缀)")
    @TableField(value = "component")
    private String component;

    @Schema(description = "权限标识")
    @TableField(value = "perm")
    private String perm;

    @Schema(description = "显示状态(1:显示;0:隐藏)")
    @TableField(value = "visible")
    private Integer visible;

    @Schema(description = "排序(数字越小排名越靠前)")
    @TableField(value = "sort")
    private Integer sort;

    @Schema(description = "菜单图标")
    @TableField(value = "icon")
    private String icon;

    @Schema(description = "跳转路径")
    @TableField(value = "redirect")
    private String redirect;

    @TableField(value = "deleted")
    @Schema(description = "删除状态(0-正常；1-已删除)")
    private Integer deleted;
}
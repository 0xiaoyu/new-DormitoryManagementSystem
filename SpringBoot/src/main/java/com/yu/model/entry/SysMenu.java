package com.yu.model.entry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yu.common.base.BaseEntity;
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
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 父节点ID路径
     */
    @TableField(value = "tree_path")
    private String treePath;

    /**
     * 菜单名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 菜单类型(1:菜单；2:目录；3:外链；4:按钮)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    @TableField(value = "path")
    private String path;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    @TableField(value = "component")
    private String component;

    /**
     * 权限标识
     */
    @TableField(value = "perm")
    private String perm;

    /**
     * 显示状态(1-显示;0-隐藏)
     */
    @TableField(value = "visible")
    private Integer visible;

    /**
     * 排序
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    private String icon;

    /**
     * 跳转路径
     */
    @TableField(value = "redirect")
    private String redirect;
}
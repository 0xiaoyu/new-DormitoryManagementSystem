package com.yu.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserAuthInfo {
    // ID
    private Long id;
    // 用户ID
    private Long userId;
    // 用户名
    private String name;
    // 密码
    private String password;
    // 状态
    private Integer status;
    // 角色
    private Set<String> roles;
    // 权限
    private Set<String> perms;
    // 数据权限
    private Integer dataScope;
}

package com.yu.security.userdetails;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.yu.model.dto.UserAuthInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SysUserDetails implements UserDetails {

    // 用户id
    private Long userId;
    // 用户名称
    private String username;
    // 用户密码
    private String password;
    // 是否启用
    private Boolean enabled;
    // 权限和角色集合
    private Collection<SimpleGrantedAuthority> authorities;
    // 数据权限
    private Set<String> perms;

    public SysUserDetails(UserAuthInfo user) {
        this.userId = user.getUserId();
        Set<String> roles = user.getRoles();
        if (CollectionUtil.isNotEmpty(roles)) {
            this.authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // 标识角色
                    .collect(Collectors.toSet());
        } else {
            this.authorities = Collections.EMPTY_SET;
        }
        this.username = user.getName();
        this.password = user.getPassword();
        this.enabled = ObjectUtil.equal(user.getStatus(), 1);
        this.perms = user.getPerms();
    }

    // 权限
    // 用户的权限集， 默认需要添加ROLE_ 前缀
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // 密码
    // 用户的加密后的密码， 不加密会使用{noop}前缀
    @Override
    public String getPassword() {
        return this.password;
    }

    // 用户名
    @Override
    public String getUsername() {
        return this.username;
    }

    // 账户是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 认证是否未过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}

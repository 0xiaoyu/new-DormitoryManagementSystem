package com.yu.common.util;

import cn.hutool.core.util.StrUtil;
import com.yu.common.constant.SecurityConstants;
import com.yu.common.constant.SystemConstants;
import com.yu.security.userdetails.SysUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 安全工具类
 *
 * @author zay
 * @since 2023/08/23
 */
public class SecurityUtils {

    public static SysUserDetails getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication))
            return null;
        Object principal = authentication.getPrincipal();
        if (principal instanceof SysUserDetails) {
            return (SysUserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public static Set<String> getRoles(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!Objects.isNull(authentication)){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (!CollectionUtils.isEmpty(authorities)){
                return authorities.stream()
                        .filter(item -> item.getAuthority().startsWith("ROLE_"))
                        .map(item -> StrUtil.removePrefix(item.getAuthority(),"ROLE_"))
                        .collect(Collectors.toSet());
            }
        }
        return Set.of();
    }

    public static boolean isRoot() {
        Set<String> roles = getRoles();
        if (roles.contains(SystemConstants.ROOT_ROLE_CODE))
            return true;
        return false;
    }

    public static Long getUserId() {
        return Objects.requireNonNull(getUser()).getUserId();
    }
}

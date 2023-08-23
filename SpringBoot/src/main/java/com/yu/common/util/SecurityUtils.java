package com.yu.common.util;

import com.yu.security.userdetails.SysUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

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
}

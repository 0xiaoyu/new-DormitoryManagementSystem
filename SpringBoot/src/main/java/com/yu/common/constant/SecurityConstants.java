package com.yu.common.constant;

/**
 * Security 常量
 * @author yu
 * @since 2023/08/20
 */
public interface SecurityConstants {

    // 登录接口路径
    String LOGIN_PATH = "/auth/login";

    // Token 前缀
    String TOKEN_PREFIX = "Bearer ";

    // 请求头Token的Key
    String TOKEN_KEY = "Authorization";

    // 验证码缓存前缀
    String VERIFY_CODE_CACHE_PREFIX = "AUTH:VERIFY_CODE:";

    // 邮箱缓存前缀
    String EMAIL_CODE_CACHE_PREFIX = "AUTH:EMAIL_CODE:";

    // 用户权限集合缓存前缀
    String USER_PERMS_CACHE_PREFIX = "AUTH:USER_PERMS:";

    // 黑名单Token缓存前缀
    String BLACK_TOKEN_CACHE_PREFIX = "AUTH:BLACK_TOKEN:";
}

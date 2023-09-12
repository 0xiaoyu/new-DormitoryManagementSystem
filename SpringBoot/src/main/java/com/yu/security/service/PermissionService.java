package com.yu.security.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.yu.common.constant.SecurityConstants;
import com.yu.util.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import java.util.Set;

/**
 * SpringSecurity 权限校验
 *
 * @author zay
 * @since 2023/8/25
 */
@Component("security")
@Slf4j
public class PermissionService {

    @Resource
    private RedisTemplate<String,Set<String>> redisTemplate;

    /**
     * 判断当前登录用户是否拥有操作权限
     *
     * @param perm 权限标识(eg: sys:user:add)
     * @return 是否有权限
     */
    public boolean hasPerm(String perm) {

        if (StrUtil.isBlank(perm)) {
            return false;
        }
        // 超级管理员放行
        if (SecurityUtils.isRoot()) {
            return true;
        }

        Long userId = SecurityUtils.getUserId();

        Set<String> perms = redisTemplate.opsForValue().get(SecurityConstants.USER_PERMS_CACHE_PREFIX + userId); // 权限数据用户登录成功节点存入redis，详见 JwtTokenManager#createToken()

        if (CollectionUtil.isEmpty(perms)) {
            return false;
        }
        boolean hasPermission = perms.stream()
                .anyMatch(item -> PatternMatchUtils.simpleMatch(perm, item)); // *号匹配任意字符

        if (!hasPermission) {
            log.error("用户无访问权限");
        }
        return hasPermission;
    }


}

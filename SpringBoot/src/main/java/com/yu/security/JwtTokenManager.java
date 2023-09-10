package com.yu.security;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.*;
import com.yu.common.constant.SecurityConstants;
import com.yu.security.userdetails.SysUserDetails;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.codec.DecodingException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JWT token 管理器
 *
 * @author haoxr
 * @since 2022/10/22
 */
@Component
public class JwtTokenManager {

    /**
     * token加密密钥
     */
    @Value("${auth.token.secret_key}")
    private String secretKey;

    /**
     * token有效期(单位:秒)
     */
    @Value("${auth.token.ttl}")
    private Integer tokenTtl;

    /**
     * 密钥字节数组
     */
    private byte[] secretKeyBytes;

    @Resource
    private RedisTemplate<String, Set<String>> redisTemplate;

    /**
     * 创建token
     *
     * @param authentication auth info
     * @return token
     */
    public String createToken(Authentication authentication) {
        SysUserDetails userDetails = (SysUserDetails) authentication.getPrincipal();

        // 角色放入JWT的claims
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        // 权限数据多放入Redis
        Set<String> perms = userDetails.getPerms();
        redisTemplate.opsForValue().set(SecurityConstants.USER_PERMS_CACHE_PREFIX + userDetails.getUserId(), perms);

        Map<String, Object> claims = Map.of(
                JWTPayload.ISSUED_AT, DateTime.now(),
                JWTPayload.EXPIRES_AT, DateTime.now().offset(DateField.SECOND, tokenTtl),
                "jti", IdUtil.fastSimpleUUID(),
                "userId", userDetails.getUserId(),
                "username", userDetails.getUsername(),
                "authorities", roles);

        return JWTUtil.createToken(claims, getSecretKeyBytes());
    }

    /**
     * 获取认证信息
     */
    public Authentication getAuthentication(Claims claims) {
        SysUserDetails principal = new SysUserDetails();
        principal.setUserId(Convert.toLong(claims.getClaim("userId"))); // 用户ID
        principal.setUsername(Convert.toStr(claims.getClaim("username"))); // 用户名
        List<SimpleGrantedAuthority> authorities = Convert.toList(String.class, claims.getClaim("authorities"))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * 解析 & 验证 token
     */
    public Claims parseAndValidateToken(String token) {
        // 解析成功说明JWT有效
        Claims claims = this.getTokenClaims(token);
        // 验证JWT 是否在黑名单(注销场景会存入黑名单)
        Boolean isBlack = redisTemplate.hasKey(SecurityConstants.BLACK_TOKEN_CACHE_PREFIX + claims.getClaim("jti"));

        if (Boolean.TRUE.equals(isBlack)) {
            throw new RuntimeException("token 已被禁用");
        }
        return claims;
    }

    public byte[] getSecretKeyBytes() {
        if (secretKeyBytes == null) {
            try {
                secretKeyBytes = Base64.decode(secretKey);
            } catch (DecodingException e) {
                secretKeyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
            }
        }
        return secretKeyBytes;
    }


    /**
     * get token claims
     */
    public Claims getTokenClaims(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        boolean b = jwt.setKey(getSecretKeyBytes()).verify();
        JWTValidator.of(token).validateDate(DateUtil.date());
        return b ? jwt.getPayload() : null;
    }


    public Date getExpiration(Claims claims) {
        return getClaim(claims, JWTPayload.EXPIRES_AT, Date.class);
    }

    public <T> T getClaim(Claims claims, String key, Type tClass) {
        return Convert.convertQuietly(tClass, claims.getClaim(key));
    }
}

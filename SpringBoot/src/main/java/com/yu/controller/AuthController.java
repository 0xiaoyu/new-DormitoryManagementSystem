package com.yu.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.Claims;
import com.yu.common.constant.SecurityConstants;
import com.yu.common.result.Result;
import com.yu.common.util.RequestUtils;
import com.yu.model.dto.CaptchaResult;
import com.yu.security.JwtTokenManager;
import com.yu.security.captcha.EasyCaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Tag(name = "01-认证中心")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final EasyCaptchaService easyCaptchaService;
    private final JwtTokenManager jwtTokenManager;
    private final RedisTemplate redisTemplate;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<LoginResult> login(
            @Parameter(description = "用户名", example = "admin") @RequestParam String username,
            @Parameter(description = "密码", example = "123456") @RequestParam String password
    ) {
        // 存储username和password
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username.toLowerCase().trim(),
                password
        );
        // 验证用户名和密码
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        // 生成token
        String accessToken = jwtTokenManager.createToken(authentication);
        // 返回token
        LoginResult loginResult = LoginResult.builder()
                .tokenType("Bearer")
                .accessToken(accessToken)
                .build();
        return Result.success(loginResult);
    }

    @Operation(summary = "注销", security = {@SecurityRequirement(name = SecurityConstants.TOKEN_KEY)})
    @DeleteMapping("/logout")
    public Result<String> logout(HttpServletRequest request) {
        String token = RequestUtils.resolveToken(request);
        if (StrUtil.isNotBlank(token)) {
            Claims claims = jwtTokenManager.getTokenClaims(token);
            String jti = StrUtil.toString(claims.getClaim("jti"));

            Date expiration = jwtTokenManager.getExpiration(claims);
            if (expiration != null) {
                // 有过期时间，在token有效时间内存入黑名单，超出时间移除黑名单节省内存占用
                long ttl = (expiration.getTime() - System.currentTimeMillis());
                redisTemplate.opsForValue().set(SecurityConstants.BLACK_TOKEN_CACHE_PREFIX + jti, null, ttl, TimeUnit.MILLISECONDS);
            } else {
                // 无过期时间，永久加入黑名单
                redisTemplate.opsForValue().set(SecurityConstants.BLACK_TOKEN_CACHE_PREFIX + jti, null);
            }
        }
        SecurityContextHolder.clearContext();
        return Result.success("注销成功");
    }

    @Operation(summary = "获取验证码")
    @GetMapping("captcha")
    public Result<CaptchaResult> getCaptcha() {
        CaptchaResult captcha = easyCaptchaService.getCaptcha();
        return Result.success(captcha);
    }


    @Schema(description ="登录响应对象")
    @Builder
    public static record LoginResult(
            @Schema(description = "访问token")
            String accessToken,

            @Schema(description = "token 类型",example = "Bearer")
            String tokenType,

            @Schema(description = "刷新token")
            String refreshToken,

            @Schema(description = "过期时间(单位：毫秒)")
            Long expires
    ) {
    }

}

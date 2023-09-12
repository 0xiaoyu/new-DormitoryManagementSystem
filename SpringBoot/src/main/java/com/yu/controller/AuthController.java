package com.yu.controller;

import com.yu.common.constant.SecurityConstants;
import com.yu.common.result.Result;
import com.yu.model.dto.CaptchaResult;
import com.yu.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "01-认证中心")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<LoginResult> login(
            @Parameter(description = "用户名/邮箱", example = "admin/zh@qq.com") @RequestParam String username,
            @Parameter(description = "密码", example = "123456") @RequestParam String password
    ) {
        String accessToken = authService.login(username, password);
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
        authService.logout();
        return Result.success("注销成功");
    }

    @Operation(summary = "获取验证码")
    @GetMapping("captcha")
    public Result<CaptchaResult> getCaptcha() {
        CaptchaResult captcha = authService.getCaptcha();
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

package com.yu.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description ="验证码响应对象")
@Builder
public record CaptchaResult(
        @Schema(description = "验证码缓存key")
        String verifyCodeKey,
        @Schema(description = "验证码图片Base64字符串")
        String verifyCodeBase64
) {

}

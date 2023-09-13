package com.yu.security.exception;


import com.yu.common.result.ResultCode;
import com.yu.util.ResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证异常处理
 *
 * @author zay
 * @since 2023/8/25
 */
@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        int status = response.getStatus();
        if (status == HttpServletResponse.SC_NOT_FOUND) {
            // 资源不存在
            ResponseUtils.writeErrMsg(response, ResultCode.RESOURCE_NOT_FOUND);
        } else {
            // 未认证或者token过期
            ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
        }
    }
}

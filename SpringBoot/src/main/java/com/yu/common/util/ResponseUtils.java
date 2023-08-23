package com.yu.common.util;

import cn.hutool.json.JSONObject;
import com.yu.common.result.Result;
import com.yu.common.result.ResultCode;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

public class ResponseUtils {

    /**
     * 异常消息返回(适用过滤器中处理异常响应)
     *
     * @param response   响应对象
     * @param resultCode 异常枚举
     */
    public static void writeErrMsg(HttpServletResponse response, ResultCode resultCode) throws IOException {
        int status = switch (resultCode) {
            case ACCESS_UNAUTHORIZED, TOKEN_INVALID -> HttpStatus.UNAUTHORIZED.value();
            case TOKEN_ACCESS_FORBIDDEN -> HttpStatus.FORBIDDEN.value();
            default -> HttpStatus.BAD_REQUEST.value();
        };
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        Result<Object> failed = Result.failed(resultCode);
        JSONObject responseData = new JSONObject().putOnce("data", failed.data()).putOnce("code", failed.code()).putOnce("msg", failed.msg());
        response.getWriter().print(responseData);
    }
}

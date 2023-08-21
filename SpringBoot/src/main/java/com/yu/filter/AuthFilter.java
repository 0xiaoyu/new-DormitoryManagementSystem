package com.yu.filter;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.jwt.Claims;
import com.yu.common.constant.SecurityConstants;
import com.yu.common.result.ResultCode;
import com.yu.common.util.RequestUtils;
import com.yu.common.util.ResponseUtils;
import com.yu.security.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    private static final AntPathRequestMatcher LOGIN_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(SecurityConstants.LOGIN_PATH, "POST");

    private final JwtTokenManager tokenManager;

    public AuthFilter(JwtTokenManager jwtTokenManager) {
        this.tokenManager = jwtTokenManager;
    }

    public static final String VERIFY_CODE_PARAM_KEY = "verifyCode";
    public static final String VERIFY_CODE_KEY_PARAM_KEY = "verifyCodeKey";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (LOGIN_PATH_REQUEST_MATCHER.matches(request)) {
            String code = request.getParameter(VERIFY_CODE_PARAM_KEY);
            String verifyCodeKey = request.getParameter(VERIFY_CODE_KEY_PARAM_KEY);

            // 由于这个不是bean，不能通过注入的方式获取，所以通过SpringUtil工具类获取
            RedisTemplate redisTemplate = SpringUtil.getBean("redisTemplate", RedisTemplate.class);
            String cacheCode =  Convert.toStr(redisTemplate.opsForValue().get(SecurityConstants.VERIFY_CODE_CACHE_PREFIX + verifyCodeKey));
            if (cacheCode == null) {
                // 验证码过期
                ResponseUtils.writeErrMsg(response, ResultCode.VERIFY_CODE_TIMEOUT);
                return;
            }
            if (!StrUtil.equals(cacheCode,code)) {
                // 验证码错误
                ResponseUtils.writeErrMsg(response, ResultCode.VERIFY_CODE_ERROR);
                return;
            }
        }else{
            String jwt = RequestUtils.resolveToken(request);
            if (StringUtils.hasText(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    Claims claims = this.tokenManager.parseAndValidateToken(jwt);
                    Authentication authentication = this.tokenManager.getAuthentication(claims);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (Exception e) {
                    ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
                }
            } else {
                ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
            }
        }
        chain.doFilter(request, response);
    }
}

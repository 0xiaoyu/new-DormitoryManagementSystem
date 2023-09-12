package com.yu.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.Claims;
import com.yu.common.constant.SecurityConstants;
import com.yu.security.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

import java.security.Principal;

/**
 * Websocket 连接认证拦截器
 *
 * @author haoxr
 * @since 2.4.0
 */
@Component
@RequiredArgsConstructor
public class AuthChannelInterceptor implements ChannelInterceptor {

    private final JwtTokenManager jwtTokenManager;

    /**
     * 连接前监听
     *
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        assert accessor != null;

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            // get token from header
            String token = accessor.getFirstNativeHeader("Authorization");
            assert token != null;
            // if token is not null
            if (StrUtil.isNotBlank(token)) {
                token = token.substring(SecurityConstants.TOKEN_PREFIX.length());
                Claims claims = jwtTokenManager.parseAndValidateToken(token);

                String username = StrUtil.toString(claims.getClaim("username"));
                // if the username is not null, assign it to the Principal.
                if (StrUtil.isNotBlank(username)) {
                    Principal principal = () -> username;
                    accessor.setUser(principal);
                    return message;
                }
            }
        }
        return ChannelInterceptor.super.preSend(message, channel);
    }

}

package com.yu.config;

import com.yu.common.constant.SecurityConstants;
import com.yu.filter.AuthFilter;
import com.yu.security.JwtTokenManager;
import com.yu.security.exception.MyAccessDeniedHandler;
import com.yu.security.exception.MyAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MyAuthenticationEntryPoint authenticationEntryPoint;
    private final MyAccessDeniedHandler accessDeniedHandler;
    private final JwtTokenManager jwtTokenManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requestMatcherRegistry ->
                        requestMatcherRegistry.requestMatchers(SecurityConstants.LOGIN_PATH).permitAll()
                                .anyRequest().authenticated())
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler))
                .csrf(AbstractHttpConfigurer::disable);

        // 验证码校验过滤器
//        http.addFilterBefore(new VerifyCodeFilter(), UsernamePasswordAuthenticationFilter.class);
        // JWT 校验过滤器
//        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenManager), UsernamePasswordAuthenticationFilter.class);
        // 请求拦截器
        http.addFilterBefore(new AuthFilter(jwtTokenManager), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    /**
     * 不走过滤器链的放行配置
     * 默认放行静态资源、登录接口、验证码接口、Swagger接口文档
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(
                        "/auth/captcha",
                        "/webjars/**",
                        "/doc.html",
                        "/swagger-resources/**",
                        "/swagger-ui/**",
                        "/ws/**"
                );
    }

    // 密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理器
     *
     * @param authenticationConfiguration 认证配置
     * @return 认证管理器
     * @throws Exception 异常
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

package com.yu.config;

import com.yu.common.enums.CaptchaTypeEnum;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

/**
 * EasyCaptcha 配置类
 * 
 * @author haoxr
 * @since 2023/03/24
 */
@ConfigurationProperties(prefix = "easy-captcha")
@Configuration
@Data
public class CaptchaConfig {

    // 验证码类型
    private CaptchaTypeEnum type = CaptchaTypeEnum.ARITHMETIC;

    // 验证码缓存过期时间(单位:秒)
    @Value("${captcha.ttl}")
    private long ttl;

    // 内容长度
    @Value("${captcha.length}")
    private int length;
    // 宽度
    @Value("${captcha.width}")
    private int width;
    // 验证码高度
    @Value("${captcha.height}")
    private int height;

    // 验证码字体
    @Value("${captcha.font-name}")
    private String fontName;

    // 字体风格
    private Integer fontStyle = Font.PLAIN;

    // 字体大小
    @Value("${captcha.font-size}")
    private int fontSize;

}

package com.yu.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.yu.common.constant.SecurityConstants;
import com.yu.common.enums.EmailType;
import com.yu.common.result.Result;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class EmailUtils {

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.ttl}")
    public Long ttl;

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    /**
     * 验证邮箱验证码是否正确
     * @param email
     * @param emailCode
     * @return
     */
    public EmailType verify(String email,String emailCode,EmailType type){
        String s = redisTemplate.opsForValue().get(SecurityConstants.EMAIL_CODE_CACHE_PREFIX.formatted(type) + email);
        if (s == null) return EmailType.OVERDUE;
        if (StrUtil.equals(s, emailCode)) {
            redisTemplate.delete(email);
            return EmailType.SUCCESS;
        }
        else
            return EmailType.FAIL;
    }

    /**
     * 发送邮箱验证码
     * @param email 邮箱
     * @param type 邮件类型
     */
    public void sendMailCode(String email,EmailType type){
        String s = redisTemplate.opsForValue().get(SecurityConstants.EMAIL_CODE_CACHE_PREFIX.formatted(type) + email);
        if (s != null) return;
        String code = RandomUtil.randomString(8);
        redisTemplate.opsForValue().set(SecurityConstants.EMAIL_CODE_CACHE_PREFIX.formatted(type) + email, code
                , ttl, TimeUnit.SECONDS);
        sendMailCode(email,code,type);
    }

    /**
     * 发送邮件验证码
     * @param to 邮箱
     * @param code 验证码
     * @param type 邮件类型
     * @return 是否发送成功
     */
    public boolean sendMailCode(String to, String code, EmailType type){
        return switch (type){
            case REGISTER ->
                    sendMailCode(to,code,"注册验证码","您好，本次验证码为：%s,有效时间%d分钟,请勿泄露给他人。".formatted(code,ttl/60));
            case RESET_PASSWORD ->
                    sendMailCode(to,code,"重置密码验证码","您好，本次验证码为：%s,有效时间%d分钟,请勿泄露给他人。".formatted(code,ttl/60));
            default -> false;
        };
    }

    /**
     * 发送邮件验证码
     * @param to 邮箱
     * @param code  验证码
     * @param subject 主题
     * @param text 内容
     * @return 是否发送成功
     */
    public boolean sendMailCode(String to,String code,String subject,String text){
        JavaMailSender mailSender = SpringUtil.getBean(JavaMailSender.class);
        //简单邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        log.info(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("x宿舍管理系统"+subject);
        simpleMailMessage.setText("您好，本次验证码为：%s,有效时间%d分钟,请勿泄露给他人。".formatted(code,ttl/60));
        try {
            mailSender.send(simpleMailMessage);
            return true;
        } catch (MailSendException e){
            log.error("邮件发送失败",e);
            return false;
        } catch (MailAuthenticationException e){
            log.error("身份验证失败",e);
            return false;
        } catch (MailParseException e){
            log.error("邮件解析失败",e);
            return false;
        }
    }
}

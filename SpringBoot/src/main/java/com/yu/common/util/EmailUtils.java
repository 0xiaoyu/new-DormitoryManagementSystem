package com.yu.common.util;

import cn.hutool.extra.spring.SpringUtil;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class EmailUtils {


    private static String from;

    public static final Long ttl = 2*60L;

    @Value("${spring.mail.username}")
    public void setFrom(String froms) {
        from = froms;
    }



    public static boolean sendMailCode(String to,String code){
        JavaMailSender mailSender = SpringUtil.getBean(JavaMailSender.class);
        //简单邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        log.info(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("xxx宿舍管理系统注册验证码");
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

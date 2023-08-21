package com.yu.authTest;

import com.wf.captcha.base.Captcha;
import com.yu.security.captcha.EasyCaptchaProducer;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@SpringBootTest
public class CaptchaTest {
    @Resource
    private EasyCaptchaProducer easyCaptchaProducer;

    @Test
    public void getCaptcha() throws FileNotFoundException {
        Captcha captcha = easyCaptchaProducer.getCaptcha();
        try (OutputStream ops = new FileOutputStream("d://captcha.jpg")) {
            captcha.out(ops);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(captcha.text());
    }
}

package com.yu.UtilsTest;

import cn.hutool.core.util.RandomUtil;
import com.yu.common.constant.SecurityConstants;
import com.yu.common.enums.EmailType;
import com.yu.common.util.EmailUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.PortResolverImpl;

@SpringBootTest
public class EmailUtilTest {

    @Autowired
    private EmailUtils emailUtils;
    @Test
    public void sendOne(){
        boolean b = emailUtils.sendMailCode("zay1647022985@foxmail.com","123", EmailType.REGISTER);
        assert b;
    }
    @Test
    public void codeTest(){
        System.out.println(RandomUtil.randomString(8));
        System.out.println(RandomUtil.randomString(9));
        System.out.println(RandomUtil.randomString(10));
    }

    @Test
    public void formatTest(){
        System.out.println(SecurityConstants.EMAIL_CODE_CACHE_PREFIX.formatted(EmailType.FAIL));
    }
}

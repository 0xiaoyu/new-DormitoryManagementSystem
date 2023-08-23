package com.yu.UtilsTest;

import cn.hutool.core.util.RandomUtil;
import com.yu.common.util.EmailUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailUtilTest {

    @Test
    public void sendOne(){
        boolean b = EmailUtils.sendMailCode("zay1647022985@foxmail.com", "123456");
        assert b;
    }
    @Test
    public void codeTest(){
        System.out.println(RandomUtil.randomString(8));
        System.out.println(RandomUtil.randomString(9));
        System.out.println(RandomUtil.randomString(10));
    }
}

package com.yu.authTest;

import com.yu.model.dto.UserAuthInfo;
import com.yu.service.impl.SysUserServiceImpl;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysUserTest {
    @Resource
    private SysUserServiceImpl service;
    @Test
    public void test(){
        UserAuthInfo root = service.getUserAuthInfo("root");

    }


}

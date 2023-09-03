package com.yu.service;

import com.yu.service.SysMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysMenuTest {

    @Autowired
    private SysMenuService menuService;

    @Test
    public void testListRoutes() {
        menuService.listRoutes();
    }
}

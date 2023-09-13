package com.yu.controller;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeControllerTest {

    @Resource
    private NoticeController controller;
    @Test
    void getNoticeType() {
        System.out.println(controller.getNoticeType());
    }

    @Test
    void getByUserId() {
    }

    @Test
    void getNoReadCount() {
    }

    @Test
    void save() {
    }
}
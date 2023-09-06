package com.yu.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceImplTest {

    @Resource
    private StudentServiceImpl service;
    @Test
    void getStudentInfo() {
        System.out.println(service.getStudentInfo("1"));
    }
}
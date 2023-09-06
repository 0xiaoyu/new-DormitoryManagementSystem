package com.yu.mapper;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentMapperTest {

    @Resource
    private StudentMapper mapper;
    @Test
    void getStudentInfo() {
        System.out.println(mapper.getStudentInfoAndV("1"));
    }
}
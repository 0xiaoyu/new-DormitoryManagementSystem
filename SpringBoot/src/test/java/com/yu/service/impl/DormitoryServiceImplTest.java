package com.yu.service.impl;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DormitoryServiceImplTest {

    @Resource
    private DormitoryServiceImpl dormitoryService;
    @Test
    void getStudentIdsByBuildingId() {
        System.out.println(dormitoryService.getSysIdsByBuildingId("1,2,3"));
    }
}
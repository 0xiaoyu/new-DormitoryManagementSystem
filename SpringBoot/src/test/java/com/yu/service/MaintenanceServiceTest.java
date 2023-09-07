package com.yu.service;

import com.yu.model.query.MaintenPageQuery;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MaintenanceServiceTest {

    @Resource
    private MaintenanceService service;
    @Test
    void getPageByCondition() {
        MaintenPageQuery q = new MaintenPageQuery();
        q.setPageNum(1);
        q.setPageSize(10);
        service.getPageByCondition(q).getRecords().forEach(System.out::println);
    }
}
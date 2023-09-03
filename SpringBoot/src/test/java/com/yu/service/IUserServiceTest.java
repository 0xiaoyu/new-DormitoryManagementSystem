package com.yu.service;

import com.yu.common.enums.UsetTypeEnum;
import com.yu.model.query.TbUserPageQuery;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IUserServiceTest {

    @Resource
    private IUserService service;
    @Test
    void pageDormitory() {
        TbUserPageQuery query = new TbUserPageQuery(null, UsetTypeEnum.DORMITORY);
        service.pageDormitory(query).getRecords().forEach(System.out::println);
    }

    @Test
    void pageRepair() {
        TbUserPageQuery query = new TbUserPageQuery(null, UsetTypeEnum.REPAIR);
        service.pageRepair(query).getRecords().forEach(System.out::println);
    }
}
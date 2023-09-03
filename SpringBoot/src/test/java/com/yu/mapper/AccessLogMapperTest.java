package com.yu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.query.PassLogPageQuery;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class AccessLogMapperTest {

    @Resource
    private AccessLogMapper accessLogMapper;

    @Test
    void getPageQuery() {
        PassLogPageQuery q = new PassLogPageQuery();
        q.setStartTime(LocalDateTime.of(2023, 9, 1, 0, 0, 0));
        q.setEndTime(LocalDateTime.of(2023, 9, 4, 0, 0, 0));
        accessLogMapper.getPageQuery(new Page<>(1, 10),
                true,
                false,
                q).getRecords().forEach(System.out::println);
    }
}
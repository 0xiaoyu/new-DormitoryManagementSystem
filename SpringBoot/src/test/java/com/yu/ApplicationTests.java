package com.yu;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.junit.jupiter.api.Test;


class ApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(LocalDateTimeUtil.of(DateUtil.parse("2023-08-31T16:00:00.000Z")));
    }

}

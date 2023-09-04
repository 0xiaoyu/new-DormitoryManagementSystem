package com.yu.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UsetTypeEnum {
    DORMITORY(1, "宿舍"),
    // 维修人员
    REPAIR(2, "维修人员"),
    // 保洁
    CLEAN(3, "保洁"),
    // 干部
    CADRE(4, "干部");
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String name;
}

package com.yu.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ViolationTypeEnum {
    WANGUI(1,"晚归");

    @EnumValue
    private final Integer value;
    @JsonValue
    private final String desc;

}

package com.yu.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ViolationTypeEnum {
    WANGUI(1,"晚归");

    @EnumValue
    private Integer value;
    private String desc;

}

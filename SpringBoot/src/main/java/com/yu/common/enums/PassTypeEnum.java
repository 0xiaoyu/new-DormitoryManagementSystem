package com.yu.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PassTypeEnum {
    STUDENT_IN(0,"学生进入"),

    STUDENT_OUT(1,"学生离开"),

    OTHER_IN(2,"其他人员进入"),

    OTHER_OUT(3,"其他人员离开");

    @EnumValue
    final Integer code;

    final String desc;
}

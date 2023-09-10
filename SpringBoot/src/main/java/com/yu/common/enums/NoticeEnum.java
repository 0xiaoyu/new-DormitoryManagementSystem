package com.yu.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoticeEnum {
    GROUP_NOTICE(1, "群通知"),
    PRIVATE_NOTICE(2, "私聊通知"),
    SYSTEM_NOTICE(3, "系统通知"),
    ;
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String msg;
}

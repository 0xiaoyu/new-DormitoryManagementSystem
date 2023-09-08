package com.yu.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NoticeEnum {
    GROUP_NOTICE(1, "群通知"),

    ;
    private final Integer code;
    private final String msg;
}

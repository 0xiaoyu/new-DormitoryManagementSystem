package com.yu.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaintenStatusEnum {
    //待支付
    WAIT_PAY(0, "待支付"),
    //待维修
    WAIT_MAINTEN(1, "待维修"),
    //完成
    FINISH(2, "完成"),
    //异常
    ERROR(3, "异常")
    ;

    @EnumValue
    private final Integer value;

    @JsonValue
    private final String label;
}

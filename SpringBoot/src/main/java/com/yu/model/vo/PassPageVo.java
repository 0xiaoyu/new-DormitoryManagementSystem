package com.yu.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "进入记录")
@Data
public class PassPageVo {

    @Schema(description = "日志id")
    private Long id;
    @Schema(description = "进出人员名字")
    private String name;

    @Schema(description = "进出时间")
    private String createTime;

    @Schema(description = "进出类型 0 进 1出")
    private Integer aType;

    @Schema(description = "宿舍号")
    private String dormitory;

    @Schema(description = "电话")
    private String phone;
}

package com.yu.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "违规记录分页")
public class ViolationLogPageVo {

    @Schema(description = "日志id")
    private Long id;

    @Schema(description = "学生姓名")
    private String name;

    @Schema(description = "违规类型")
    private Integer type;

    @Schema(description = "状态")
    private Integer flag;

    @Schema(description = "违规详情")
    private String detail;
}

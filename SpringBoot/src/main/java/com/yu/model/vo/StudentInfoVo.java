package com.yu.model.vo;

import com.yu.model.entity.PayLogEntity;
import com.yu.model.entity.ViolationLog;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "学生信息")
public class StudentInfoVo {
    @Schema(description = "学生姓名")
    private String name;

    @Schema(description = "宿舍")
    private String dormitory;

    @Schema(description = "电费")
    private Double electricity;

    @Schema(description = "水费")
    private Double water;

    @Schema(description = "缴费记录")
    private List<PayLogEntity> payLogs;

    @Schema(description = "违规记录")
    private List<ViolationLog> violationLogs;
}

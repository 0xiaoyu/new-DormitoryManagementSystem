package com.yu.model.vo;

import com.yu.common.enums.MaintenStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description ="维护分页对象")
public class MaintenPageVo {

    @Schema(description="维修单号")
    private Long id;

    @Schema(description = "学生名字")
    private String studentName;

    @Schema(description = "维修人员名字")
    private String repairName;

    @Schema(description = "维修人员电话")
    private String repairPhone;

    @Schema(description = "学生电话")
    private String studentPhone;

    @Schema(description = "维修类型id")
    private String typeId;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "维修状态")
    private MaintenStatusEnum status;

    @Schema(description = "更新时间")
    private String updateTime;

    @Schema(description = "维修宿舍")
    private String dormitoryNumber;

    @Schema(description = "维修宿舍楼id")
    private Long buildingId;

    @Schema(description = "维修详情描述")
    private String detail;

}

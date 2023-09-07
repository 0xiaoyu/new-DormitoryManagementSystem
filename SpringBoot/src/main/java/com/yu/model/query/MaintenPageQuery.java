package com.yu.model.query;

import com.yu.common.base.BasePageQuery;
import com.yu.common.enums.MaintenStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintenPageQuery extends BasePageQuery {
    /**
     * 宿舍号
     */
    @Schema(description = "宿舍号")
    private Long dormitoryId;

    /**
     * 请求的学生,通过学生获取电话
     */
    @Schema(description = "请求的学生,通过学生获取电话")
    private Integer student;

    /**
     * 维修状态，0待支付，1待维修，2完成，3异常
     */
    @Schema(description = "维修状态，0待支付，1待维修，2完成，3异常")
    private MaintenStatusEnum status;

    /**
     * 维修人员id,通过维修人员表获取电话
     */
    @Schema(description = "维修人员id,通过维修人员表获取电话")
    private Integer maintenancePersonId;

    /**
     * 维修的类型id
     */
    @Schema(description = "维修的类型id")
    private Integer typeId;
}

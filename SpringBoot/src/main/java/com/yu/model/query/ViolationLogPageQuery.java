package com.yu.model.query;

import com.yu.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "违规记录分页查询对象")
@Data
public class ViolationLogPageQuery extends BasePageQuery {

    @Schema(description = "学生姓名")
    private String name;

    @Schema(description = "类型")
    private Integer type;

    @Schema(description = "状态")
    private Integer flag;
}

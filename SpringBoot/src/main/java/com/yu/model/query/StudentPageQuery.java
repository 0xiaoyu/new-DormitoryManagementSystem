package com.yu.model.query;

import com.yu.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "学生分页查询对象")
@Data
public class StudentPageQuery extends BasePageQuery {

    @Schema(description = "学号")
    private Long id;

    @Schema(description = "学生姓名")
    private String name;

    @Schema(description = "班级")
    private String classId;

    @Schema(description = "宿舍id")
    private String  dormitoryId;

    @Schema(description = "性别 1男 2女")
    private Integer gender;
}

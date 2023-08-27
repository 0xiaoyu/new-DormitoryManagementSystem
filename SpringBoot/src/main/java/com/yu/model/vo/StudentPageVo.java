package com.yu.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "学生分页查询对象")
@Data
public class StudentPageVo {

    @Schema(description = "学号")
    private Long id;

    @Schema(description = "学生姓名")
    private String studentName;

    @Schema(description = "性别 1男 2女")
    private Integer gender;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "班级")
    private Integer classId;

    @Schema(description = "宿舍名称")
    private String buildName;

    @Schema(description = "宿舍号")
    private String dormitoryNumber;



}

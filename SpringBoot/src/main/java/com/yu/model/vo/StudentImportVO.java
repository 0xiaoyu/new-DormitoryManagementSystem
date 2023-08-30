package com.yu.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.yu.common.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 用户导入对象
 *
 * @author haoxr
 * @since 2022/4/10
 */
@Data
@Schema(description = "学生导出对象")
public class StudentImportVO {

    @ExcelProperty(value = "学号")
    private Long id;

    @ExcelProperty(value = "学生姓名")
    private String name;

    @ExcelProperty(value = "性别")
    private GenderEnum gender;

    @ExcelProperty(value = "年龄")
    private Integer age;

    @ExcelProperty(value = "手机号码")
    private String mobile;

    @ExcelProperty(value = "宿舍名称")
    private String dormitoryName;

    @ExcelProperty(value = "宿舍号")
    private String dormitoryNumber;

    @ExcelProperty("班级")
    private String classCodes;

}

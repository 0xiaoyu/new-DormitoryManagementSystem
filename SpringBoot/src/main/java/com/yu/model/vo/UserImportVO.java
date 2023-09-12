package com.yu.model.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 用户导入对象
 *
 * @author zay
 * @since 2023/8/25
 */
@Data
public class UserImportVO {

    @ExcelProperty(value = "用户名")
    private String name;

    @ExcelProperty(value = "用户id")
    private String userId;

    @ExcelProperty(value = "邮箱")
    private String email;

    @ExcelProperty("角色")
    private String roleCodes;

}

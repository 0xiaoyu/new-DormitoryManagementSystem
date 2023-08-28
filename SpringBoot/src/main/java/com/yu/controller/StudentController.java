package com.yu.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yu.common.annotation.PreventDuplicateSubmit;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.common.util.CommonUtil;
import com.yu.listener.easyexcel.UserImportListener;
import com.yu.model.entity.Student;
import com.yu.model.query.StudentPageQuery;
import com.yu.model.vo.StudentImportVO;
import com.yu.model.vo.StudentPageVo;
import com.yu.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/v1/student")
@Tag(name = "学生接口")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("/page")
    @Operation(summary = "学生分页列表", security = {@SecurityRequirement(name = "Authorization")})
    public PageResult<StudentPageVo> page(@ParameterObject StudentPageQuery queryParams) {
        IPage<StudentPageVo> result = studentService.getPage(queryParams);
        return PageResult.success(result);
    }

    @PatchMapping
    @PreAuthorize("@security.hasPerm('sys:student:add')")
    @PreventDuplicateSubmit
    @Operation(summary = "新增学生", security = {@SecurityRequirement(name = "Authorization")})
    public Result<Boolean> saveOrUpdateStudent(@RequestBody StudentPageVo student) {
        Assert.notEmpty(student.getBuildName(), "楼栋名称不能为空");
        Assert.notEmpty(student.getDormitoryNumber(), "楼栋名称不能为空");
        return Result.success(studentService.saveOrUpdateStudent(student));
    }


    @Operation(summary = "导入用户", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/_import")
    public Result<String> importUsers(MultipartFile file) throws IOException {
        UserImportListener listener = new UserImportListener();
        String msg = CommonUtil.importExcel(file.getInputStream(), StudentImportVO.class, listener);
        return Result.success(msg);
    }

    // todo 此处应该返回一个code，先简单做了
    @Operation(summary = "验证学生身份")
    @PostMapping("/verify")
    public Result<Boolean> verifyStudent(@RequestBody Student student){
        Student one = studentService.getOne(Wrappers.lambdaQuery(student));
        if (one == null)
            return Result.failed("学生身份验证失败");
        return Result.success(true);
    }

}

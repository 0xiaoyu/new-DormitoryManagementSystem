package com.yu.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
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
import com.yu.model.vo.StudentInfoVo;
import com.yu.model.vo.StudentPageVo;
import com.yu.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/api/v1/student")
@Tag(name = "07.学生接口")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("count")
    @Operation(summary = "学生总数", security = {@SecurityRequirement(name = "Authorization")})
    public Result<Long> count() {
        return Result.success(studentService.count());
    }

    @GetMapping("info/{id}")
    @Operation(summary = "获取学生详细信息", security = {@SecurityRequirement(name = "Authorization")})
    @PreAuthorize("@security.hasPerm('sys:student:info')")
    public Result<StudentInfoVo> info(@PathVariable String id) {
        StudentInfoVo student = studentService.getStudentInfo(id);
        return Result.success(student);
    }


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
    @PreAuthorize("@security.hasPerm('sys:student:add')")
    public Result<String> importUsers(MultipartFile file) throws IOException {
        UserImportListener listener = new UserImportListener();
        String msg = CommonUtil.importExcel(file.getInputStream(), StudentImportVO.class, listener);
        return Result.success(msg);
    }

    @Operation(summary = "删除学生", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{ids}")
    @PreAuthorize("@security.hasPerm('sys:user:delete')")
    public Result<Boolean> deleteStudent(
            @Parameter(description = "学生ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的用户数据为空");
        // 逻辑删除
        boolean result = studentService.removeByIds(Arrays.stream(ids.split(","))
                .map(Long::parseLong).toList());
        return Result.judge(result);
    }

    @Operation(summary = "获取学生联系方式",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}/phone")
    @PreAuthorize("@security.hasPerm('student:phone:get')")
    public Result<String> getStudentPhone(@PathVariable Long id){
        Student phone = studentService.getOne(Wrappers.<Student>lambdaQuery().eq(Student::getId, id).select(Student::getPhone));
        return Result.success(phone.getPhone());
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

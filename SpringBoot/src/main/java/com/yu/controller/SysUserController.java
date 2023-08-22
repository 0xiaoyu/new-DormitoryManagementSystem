package com.yu.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yu.common.constant.UserRoleConstants;
import com.yu.common.result.Result;
import com.yu.model.entity.Student;
import com.yu.model.entity.SysUser;
import com.yu.model.entity.SysUserRole;
import com.yu.service.StudentService;
import com.yu.service.SysUserRoleService;
import com.yu.service.SysUserService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Tag(name = "02.系统用户接口")
@RestController
@RequestMapping("/users")
public class SysUserController {

    @Resource
    private SysUserService userService;

    @Resource
    private StudentService studentService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private SysUserRoleService userRoleService;

    @PostMapping("/saveStudent")
    @Transactional
    public Result<Object> saveStudent(@RequestBody StudentUser studentUser) {
        Student one = studentService.getOne(Wrappers.lambdaQuery(Student.class)
                .eq(Student::getId, studentUser.userId)
                .eq(Student::getPhone, studentUser.phone)
                .eq(Student::getStudentName, studentUser.sName));
        if (Objects.isNull(one)) {
            return Result.failed("学生信息不匹配");
        }
        SysUser student = new SysUser();
        BeanUtils.copyProperties(studentUser, student);
        String password = passwordEncoder.encode(studentUser.password);
        student.setPassword(password);
        boolean save = userService.save(student);
        if (save) {
            boolean b = userRoleService.save(new SysUserRole(student.getId(), UserRoleConstants.STUDENT_ROLE_ID));
            if (!b) {
                userService.removeById(student.getId());
                return Result.failed("注册学生角色失败");
            }
            return Result.success();
        } else
            return Result.failed("注册学生用户失败");
    }

    @Schema(description = "注册学生用户")
    public record StudentUser(
            @Schema(description = "学生姓名", example = "张三") String sName,
            @Schema(description = "用户名", example = "张三") String name,
            @Schema(description = "密码", example = "123") String password,
            @Schema(description = "学生邮箱", example = "zhay@outlook.com") String email,
            @Schema(description = "头像", example = "/static/a.gif") String avatar,
            @Schema(description = "学号", example = "2023050111") String userId,
            @Schema(description = "手机号", example = "12345678901") String phone
    ) {


    }
}

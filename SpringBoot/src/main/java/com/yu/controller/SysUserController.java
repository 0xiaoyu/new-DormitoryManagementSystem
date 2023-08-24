package com.yu.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.constant.UserRoleConstants;
import com.yu.common.enums.EmailType;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.common.util.EmailUtils;
import com.yu.model.entity.Student;
import com.yu.model.entity.SysUser;
import com.yu.model.entity.SysUserRole;
import com.yu.model.vo.UserInfoVO;
import com.yu.service.StudentService;
import com.yu.service.SysUserRoleService;
import com.yu.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private EmailUtils emailUtils;


    @PostMapping("saveStudent")
    @Operation(description = "注册学生用户")
    @Transactional
    public Result<Object> saveStudent(@RequestBody StudentUser studentUser,
                                      @Parameter(description = "邮箱验证码", example = "admin/zh@qq.com") @RequestParam("emailCode") String emailCode) {
        switch (emailUtils.verify(studentUser.email,emailCode)){
            case FAIL -> Result.failed("邮箱验证码错误");
            case OVERDUE -> Result.failed("邮箱验证码已过期");
        }
        Student one = studentService.getOne(Wrappers.lambdaQuery(Student.class)
                .eq(Student::getId, studentUser.userId)
                .eq(Student::getPhone, studentUser.phone)
                .eq(Student::getStudentName, studentUser.sName));
        if (Objects.isNull(one)) return Result.failed("学生信息不匹配");
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

    @PostMapping()
    @Operation(description = "注册用户")
    @PreAuthorize("@security.hasPerm('sys:user:add')")
    public Result<Object> saveUser(@RequestBody SysUser user) {
        // 密码加密
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        boolean b = userService.save(user);
        return b ? Result.success() : Result.failed("注册失败");
    }

    @GetMapping("page/{current}/{size}")
    @Operation(description = "分页查询用户")
    public PageResult<SysUser> getPageUser(@PathVariable("current") Integer current,
                                           @PathVariable("size") Integer size, SysUser user) {
        Page<SysUser> page = new Page<>(current, size);
        Page<SysUser> userPage = userService.page(page, Wrappers.lambdaQuery(user));
        return PageResult.success(userPage);
    }

    @PatchMapping("resetPassword")
    @Operation(description = "重置密码")
    public Result<String> resetPassword(@Parameter(description = "email", example = "1647@qq.com") String email,
                                        @Parameter(description = "邮箱验证码", example = "123asd") String code,
                                        @Parameter(description = "新密码", example = "123456") String password) {
        switch (emailUtils.verify(email, code)){
            case FAIL -> Result.failed("邮箱验证码错误");
            case OVERDUE -> Result.failed("邮箱验证码已过期");
        }
        SysUser user = userService.getOne(Wrappers.lambdaQuery(SysUser.class).eq(SysUser::getEmail, email));
        if (Objects.isNull(user)) return Result.failed("用户不存在");
        String encode = passwordEncoder.encode(password);
        user.setPassword(encode);
        boolean b = userService.updateById(user);
        return b ? Result.success() : Result.failed("重置密码失败");
    }

    @Operation(description = "获取邮箱验证码验证")
    @GetMapping("getEmailVerifyCode")
    public Result<String> getEmailVerifyCode(@Parameter(description = "注册的邮箱", example = "164702@qq.com") String email,
                                             @Parameter(description = "发送邮箱的类型",example = "REGISTER") EmailType type){
        try {
            emailUtils.sendMailCode(email,type);
            return Result.success();
        } catch (Exception e) {
            return Result.failed("发送验证码失败");
        }
    }

    @Operation(description = "获取用户详细信息")
    @GetMapping("me")
    public Result<UserInfoVO> getUserLoginInfo() {
        UserInfoVO info = userService.getUserLoginInfo();
        return Result.success(info);
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

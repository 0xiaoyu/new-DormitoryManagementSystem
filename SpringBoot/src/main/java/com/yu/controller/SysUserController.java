package com.yu.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.constant.SecurityConstants;
import com.yu.common.constant.UserRoleConstants;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.common.util.EmailUtils;
import com.yu.model.dto.UserAuthInfo;
import com.yu.model.entity.Student;
import com.yu.model.entity.SysUser;
import com.yu.model.entity.SysUserRole;
import com.yu.service.StudentService;
import com.yu.service.SysUserRoleService;
import com.yu.service.SysUserService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("saveStudent")
    @Schema(description = "注册学生用户")
    @Transactional
    public Result<Object> saveStudent(@RequestBody StudentUser studentUser,
                                      @Parameter(description = "邮箱验证码", example = "admin/zh@qq.com") @RequestParam("emailCode") String emailCode) {
        String s = redisTemplate.opsForValue().get(SecurityConstants.EMAIL_CODE_CACHE_PREFIX + studentUser.email);
        if (s == null) {
            return Result.failed("邮箱验证码已过期");
        }
        if (StrUtil.equals(s, emailCode)) {
            redisTemplate.delete(studentUser.email);
        } else {
            return Result.failed("邮箱验证码错误");
        }
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

    @PostMapping()
    @Schema(description = "注册用户")
    public Result<Object> saveUser(@RequestBody SysUser user) {
        // 密码加密
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        boolean b = userService.save(user);
        return b?Result.success():Result.failed("注册失败");
    }

    @GetMapping("page/{current}/{size}")
    @Schema(description = "分页查询用户")
    public PageResult<SysUser> getPageUser(@PathVariable("current") Integer current,
                                      @PathVariable("size") Integer size,SysUser user) {
        Page<SysUser> page = new Page<>(current, size);
        Page<SysUser> userPage = userService.page(page, Wrappers.lambdaQuery(user));
        return PageResult.success(userPage);
    }

    @Schema(description = "获取邮箱验证码验证")
    @GetMapping("getEmailVerifyCode")
    public Result<String> getEmailVerifyCode(@Parameter(description = "注册的邮箱", example = "164702@qq.com") String email) {
        String code = RandomUtil.randomString(8);
        redisTemplate.opsForValue().set(SecurityConstants.EMAIL_CODE_CACHE_PREFIX + email, code
                , EmailUtils.ttl, TimeUnit.SECONDS);
        try {
            EmailUtils.sendMailCode(email, code);
            return Result.success();
        } catch (Exception e) {
            return Result.failed("发送验证码失败");
        }
    }

    @Schema(description = "获取用户详细信息")
    @GetMapping("me")
    public Result<UserAuthInfo> getUserLoginInfo(){
        UserAuthInfo info = userService.getUserLoginInfo();
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

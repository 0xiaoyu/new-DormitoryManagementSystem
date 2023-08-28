package com.yu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yu.common.constant.UserRoleConstants;
import com.yu.common.enums.EmailType;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.common.util.CommonUtil;
import com.yu.common.util.EmailUtils;
import com.yu.listener.easyexcel.UserImportListener;
import com.yu.model.entity.SysUser;
import com.yu.model.entity.SysUserRole;
import com.yu.model.query.UserPageQuery;
import com.yu.model.vo.UserImportVO;
import com.yu.model.vo.UserInfoVO;
import com.yu.model.vo.UserPageVO;
import com.yu.service.SysUserRoleService;
import com.yu.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Tag(name = "02.系统用户接口")
@RestController
@RequestMapping("/api/v1/users")
public class SysUserController {

    @Resource
    private SysUserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SysUserRoleService userRoleService;
    @Resource
    private EmailUtils emailUtils;


    @PostMapping("saveStudent")
    @Operation(description = "注册学生用户")
    @Transactional
    public Result<Object> saveStudent(@Parameter(description = "邮箱验证码", example = "admin/zh@qq.com") @RequestParam("emailCode") String emailCode,
                                      @RequestBody StudentUser studentUser) {
        var s =switch (emailUtils.verify(studentUser.email,emailCode, EmailType.REGISTER)){
            case FAIL -> Result.failed("邮箱验证码错误");
            case OVERDUE -> Result.failed("邮箱验证码已过期");
            default -> null;
        };
        if (s!=null)
            return s;
        SysUser student = new SysUser();
        BeanUtils.copyProperties(studentUser, student);
        student.setName(studentUser.username);
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
    public Result<Object> saveUser(@RequestBody SysUserForm userForm) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userForm, user);
        if (StringUtils.hasText(userForm.password))
            user.setPassword("123456");
        // 密码加密
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        boolean b = userService.save(user);
        if (b) {
            List<Long> roleIds = userForm.roleIds;
            if (roleIds != null && !roleIds.isEmpty()) {
                roleIds.forEach(roleId -> userRoleService.save(new SysUserRole(user.getId(), roleId)));
            }
            return Result.success();
        } else
            return Result.failed("注册失败");
    }

    @GetMapping("page")
    @Operation(summary = "用户分页列表", security = {@SecurityRequirement(name = "Authorization")})
    public PageResult<UserPageVO> page(@ParameterObject UserPageQuery queryParams) {
        IPage<UserPageVO> result  = userService.getUserPage(queryParams);
        return PageResult.success(result);
    }

    @GetMapping("getByName")
    @Operation(description = "根据用户名获取用户")
    public Result<Boolean> getByName(@Parameter(description = "用户名", example = "admin") @RequestParam("name") String name) {
        SysUser user = userService.getOne(Wrappers.lambdaQuery(SysUser.class).select(SysUser::getId).eq(SysUser::getName, name));
        return Result.success(user==null);
    }

    @PatchMapping("resetPassword")
    @Operation(description = "重置密码")
    public Result<String> resetPassword(@Parameter(description = "email", example = "1647@qq.com") String email,
                                        @Parameter(description = "邮箱验证码", example = "123asd") String code,
                                        @Parameter(description = "新密码", example = "123456") String password) {
        Result<String> s = switch (emailUtils.verify(email, code, EmailType.RESET_PASSWORD)){
            case FAIL -> Result.failed("邮箱验证码错误");
            case OVERDUE -> Result.failed("邮箱验证码已过期");
            default -> null;
        };
        if (s!=null)
            return s;

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
                                             @Parameter(description = "发送邮箱的类型",example = "REGISTER")@RequestParam("type") EmailType type){
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

    @Operation(summary = "导入用户", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/_import")
    public Result<String> importUsers(MultipartFile file) throws IOException {
        UserImportListener listener = new UserImportListener();
        String msg = CommonUtil.importExcel(file.getInputStream(), UserImportVO.class, listener);
        return Result.success(msg);
    }

    @GetMapping("roles/{id}")
    @Operation(summary = "获取用户的角色ID集合", security = {@SecurityRequirement(name = "Authorization")})
    public Result<List<Long>> getUserRoleIds(@Parameter(description = "用户ID") @PathVariable Long id) {
        return Result.success(userRoleService.list(Wrappers.lambdaQuery(SysUserRole.class).select(SysUserRole::getRoleId).eq(SysUserRole::getUserId, id)).stream().map(SysUserRole::getRoleId).toList());
    }

    @Schema(description = "注册学生用户")
    public record StudentUser(
            @Schema(description = "用户名", example = "张三") String username,
            @Schema(description = "密码", example = "123") String password,
            @Schema(description = "学生邮箱", example = "zhay@outlook.com") String email,
            @Schema(description = "学号", example = "2023050111") Long userId
    ) {
    }

    @Schema(description = "用户表单")
    public record SysUserForm(
            @Schema(description = "用户id") Long id,
            @Schema(description = "用户名", example = "张三") String name,
            @Schema(description = "密码,最多只能 30 字符", example = "123") String password,
            @Schema(description = "用户头像", example = "") String avatar,
            @Schema(description = "邮箱", example = "") String email,
            @Schema(description = "用户状态(1:正常;0:禁用)", example = "1") Integer status,
            @Schema(description = "绑定id", example = "1") Long userId,
            @Schema(description = "绑定角色ID" ,example = "[1,2,3]") List<Long> roleIds

    ){
    }
}

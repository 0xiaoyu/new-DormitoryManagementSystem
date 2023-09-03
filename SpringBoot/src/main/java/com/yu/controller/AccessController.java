package com.yu.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.Claims;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.yu.common.constant.SecurityConstants;
import com.yu.common.enums.ViolationTypeEnum;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.config.SystemConfig;
import com.yu.model.entity.AccessLogEntity;
import com.yu.model.entity.SysUser;
import com.yu.model.entity.ViolationLog;
import com.yu.model.query.PassLogPageQuery;
import com.yu.model.vo.PassPageVo;
import com.yu.security.JwtTokenManager;
import com.yu.security.userdetails.SysUserDetails;
import com.yu.service.AccessLogService;
import com.yu.service.IUserService;
import com.yu.service.StudentService;
import com.yu.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 进入记录 控制层。
 *
 * @author yu
 * @since 1.0
 */
@RestController
@RequestMapping("api/v1/accessLog")
@Tag(name = "进入记录控制层")
public class AccessController {

    @Resource
    private AccessLogService accessLogService;
    @Resource
    private JwtTokenManager jwtTokenManager;
    @Resource
    private SysUserService userService;
    @Resource
    private StudentService studentService;
    @Resource
    private IUserService iUserService;


    @Operation(summary = "获取晚归时间")
    @GetMapping("accessTime")
    public Result<String> getTime(){
        return Result.success(SystemConfig.getTime());
    }

    @Operation(summary = "修改晚归时间")
    @PutMapping("modifyTime")
    public Result<Boolean> modifyTime(String time){
        SystemConfig.AccessControlTime = LocalTime.parse(time);
        return Result.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除记录")
    public Result<Boolean> delete(String ids){
        return Result.success(accessLogService.removeBatchByIds(StrUtil.split(ids,',')));
    }

    /**
     * 分页查询进入记录
     *
     * @param query 分页查询对象
     * @return 分页查询对象
     */
    @GetMapping("pageQuery")
    @Operation(summary = "分页查询进入记录")
    public PageResult<PassPageVo> pageQuery(@ParameterObject PassLogPageQuery query){
        return PageResult.success(accessLogService.getPageQuery(query));
    }
    /**
     * 二维码验证
     * @param token 通行token
     * @return {@code true} 验证成功，{@code false} 验证失败
     */
    @PostMapping("authentication")
    @Operation(summary = "二维码验证")
    public Result<String> authentication(@Parameter(description = "通行token")@RequestBody String token) {
       token = token.replaceAll("\"", "");
        if (StrUtil.isBlank(token) || !token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return Result.failed("验证失败");
        }
        String jwt = token.substring(SecurityConstants.TOKEN_PREFIX.length());
        Claims claims = jwtTokenManager.parseAndValidateToken(jwt);
        Authentication authentication = jwtTokenManager.getAuthentication(claims);
        SysUserDetails userDetails = (SysUserDetails) authentication.getPrincipal();
        Long id = userDetails.getUserId();
        Collection<? extends GrantedAuthority> a = authentication.getAuthorities();
        Set<String> roles = null;
        if (!CollectionUtils.isEmpty(a)){
            roles = a.stream()
                    .filter(item -> item.getAuthority().startsWith("ROLE_"))
                    .map(item -> StrUtil.removePrefix(item.getAuthority(),"ROLE_"))
                    .collect(Collectors.toSet());
        }
        SysUser user = userService.getById(id);
        Long userId = user.getUserId();
        if (Objects.isNull(userId)){
            return Result.success("管理员");
        }

        String name = null;
        if (roles!=null && roles.contains("STUDENT")) {
            if (SystemConfig.isAccessControlTime()){
                Db.save(ViolationLog.of(null,userId, ViolationTypeEnum.WANGUI,0,1,"晚归,时间"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒"))));
            }
            name = studentService.getById(userId).getStudentName();
            AccessLogEntity log = AccessLogEntity.builder()
                    .userId(userId)
                    .aType(1)
                    .build();
            accessLogService.save(log);
        } else {
            name = iUserService.getById(userId).getName();
            AccessLogEntity log = AccessLogEntity
                    .builder()
                    .userId(userId)
                    .aType(2)
                    .build();
            accessLogService.save(log);
        }
        return Result.success("欢迎:"+name+","+ LocalDateTime.now());
    }

    /**
     * 添加 进入记录
     *
     * @param accessLog 进入记录
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加进入记录")
    @Parameters(value = {
            @Parameter(name = "id", description = "进出id"),
            @Parameter(name = "userId", description = "进出id"),
            @Parameter(name = "createTime", description = "创建时间"),
            @Parameter(name = "aType", description = "进出类型 0 进 1出")
    })
    public Result<Boolean> save(@RequestBody AccessLogEntity accessLog) {
        return Result.success(accessLogService.save(accessLog));
    }


    /**
     * 根据主键删除进入记录
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除进入记录")
    @Parameters(value = {
            @Parameter(name = "id", description = "进出id", required = true)
    })
    public Result<Boolean> remove(@PathVariable Serializable id) {
        return Result.success(accessLogService.removeById(id));
    }


    /**
     * 根据主键更新进入记录
     *
     * @param accessLog 进入记录
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新进入记录")
    @Parameters(value = {
            @Parameter(name = "id", description = "进出id", required = true),

            @Parameter(name = "userId", description = "进出id"),

            @Parameter(name = "createTime", description = "创建时间"),

            @Parameter(name = "aType", description = "进出类型 0 进 1出")
    })
    public Result<Boolean> update(@RequestBody AccessLogEntity accessLog) {
        return Result.success(accessLogService.updateById(accessLog));
    }

    /**
     * 根据进入记录主键获取详细信息。
     *
     * @param id accessLog主键
     * @return 进入记录详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据进入记录主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "进出id", required = true)
    })
    public Result<AccessLogEntity> getInfo(@PathVariable Serializable id) {
        return Result.success(accessLogService.getById(id));
    }
}
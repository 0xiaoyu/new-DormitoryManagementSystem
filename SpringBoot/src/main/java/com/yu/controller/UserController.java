package com.yu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.result.Result;
import com.yu.model.entity.UserEntity;
import com.yu.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 人员表 控制层。
 *
 * @author yu
 * @since 2.0
 */
@RestController
@RequestMapping("/user")
@Tag(name = "人员表控制层")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 添加 人员表
     *
     * @param user 人员表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加人员表")
    @Parameters(value = {
            @Parameter(name = "id", description = "人员id"),

            @Parameter(name = "name", description = "宿管名字"),

            @Parameter(name = "gender", description = "性别0 男 1女"),

            @Parameter(name = "phone", description = "手机号"),

            @Parameter(name = "age", description = "年龄"),

            @Parameter(name = "deleted", description = "逻辑删除标识(0-未删除；1-已删除)")
    })
    public Result<Boolean> save(@RequestBody UserEntity user) {
        return Result.success(userService.save(user));
    }


    /**
     * 根据主键删除人员表
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除人员表")
    @Parameters(value = {
            @Parameter(name = "id", description = "人员id", required = true)
    })
    public Result<Boolean> remove(@PathVariable Serializable id) {
        return Result.success(userService.removeById(id));
    }


    /**
     * 根据主键更新人员表
     *
     * @param user 人员表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新人员表")
    @Parameters(value = {
            @Parameter(name = "id", description = "人员id", required = true),

            @Parameter(name = "name", description = "宿管名字"),

            @Parameter(name = "gender", description = "性别0 男 1女"),

            @Parameter(name = "phone", description = "手机号"),

            @Parameter(name = "age", description = "年龄"),

            @Parameter(name = "deleted", description = "逻辑删除标识(0-未删除；1-已删除)")
    })
    public Result<Boolean> update(@RequestBody UserEntity user) {
        return Result.success(userService.updateById(user));
    }


    /**
     * 查询所有人员表
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有人员表")
    public Result<List<UserEntity>> list() {
        return Result.success(userService.list());
    }


    /**
     * 根据人员表主键获取详细信息。
     *
     * @param id user主键
     * @return 人员表详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据人员表主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "人员id", required = true)
    })
    public Result<UserEntity> getInfo(@PathVariable Serializable id) {
        return Result.success(userService.getById(id));
    }


    /**
     * 分页查询人员表
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询人员表")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Result<Page<UserEntity>> page(Page<UserEntity> page) {
        return Result.success(userService.page(page));
    }
}
package com.yu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.result.Result;
import com.yu.model.entity.AccessLogEntity;
import com.yu.service.AccessLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 进入记录 控制层。
 *
 * @author yu
 * @since 1.0
 */
@RestController
@RequestMapping("/accessLog")
@Tag(name = "进入记录控制层")
public class AccessLogController {

    @Resource
    private AccessLogService accessLogService;

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
     * 查询所有进入记录
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有进入记录")
    public Result<List<AccessLogEntity>> list() {
        return Result.success(accessLogService.list());
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


    /**
     * 分页查询进入记录
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询进入记录")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Result<Page<AccessLogEntity>> page(Page<AccessLogEntity> page) {
        return Result.success(accessLogService.page(page));
    }
}
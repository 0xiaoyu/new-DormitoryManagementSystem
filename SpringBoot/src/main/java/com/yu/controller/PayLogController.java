package com.yu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.result.Result;
import com.yu.model.entity.PayLogEntity;
import com.yu.service.PayLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 电费日志 控制层。
 *
 * @author yu
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/payLog")
@Tag(name = "电费日志控制层")
public class PayLogController {

    @Resource
    private PayLogService payLogService;

    /**
     * 添加 电费日志
     *
     * @param eleLog 电费日志
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加电费日志")
    @Parameters(value = {
            @Parameter(name = "id", description = ""),

            @Parameter(name = "dormitoryId", description = "宿舍id"),

            @Parameter(name = "amount", description = "缴费金额"),

            @Parameter(name = "userId", description = "缴费者id"),

            @Parameter(name = "createTime", description = "缴费时间"),

            @Parameter(name = "status", description = "订单状态 0 未支付，1完成")
    })
    public Result<Boolean> save(@RequestBody PayLogEntity eleLog) {
        return Result.success(payLogService.save(eleLog));
    }


    /**
     * 根据主键删除电费日志
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除电费日志")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true)
    })
    public Result<Boolean> remove(@PathVariable Serializable id) {
        return Result.success(payLogService.removeById(id));
    }


    /**
     * 根据主键更新电费日志
     *
     * @param eleLog 电费日志
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新电费日志")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true),

            @Parameter(name = "dormitoryId", description = "宿舍id"),

            @Parameter(name = "amount", description = "缴费金额"),

            @Parameter(name = "userId", description = "缴费者id"),

            @Parameter(name = "createTime", description = "缴费时间"),

            @Parameter(name = "status", description = "订单状态 0 未支付，1完成")
    })
    public Result<Boolean> update(@RequestBody PayLogEntity eleLog) {
        return Result.success(payLogService.updateById(eleLog));
    }


    /**
     * 查询所有电费日志
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有电费日志")
    public Result<List<PayLogEntity>> list() {
        return Result.success(payLogService.list());
    }


    /**
     * 根据电费日志主键获取详细信息。
     *
     * @param id eleLog主键
     * @return 电费日志详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据电费日志主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true)
    })
    public Result<PayLogEntity> getInfo(@PathVariable Serializable id) {
        return Result.success(payLogService.getById(id));
    }


    /**
     * 分页查询电费日志
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询电费日志")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Result<Page<PayLogEntity>> page(Page<PayLogEntity> page) {
        return Result.success(payLogService.page(page));
    }
}
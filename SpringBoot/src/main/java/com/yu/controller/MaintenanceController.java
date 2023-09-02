package com.yu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.result.Result;
import com.yu.model.entity.MaintenanceEntity;
import com.yu.service.MaintenanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 维修详情表 控制层。
 *
 * @author yu
 * @since 1.0
 */
@RestController
@RequestMapping("/tbMaintenance")
@Tag(name = "维修人员表控制层")
public class MaintenanceController {

    @Resource
    private MaintenanceService tbMaintenanceService;

    /**
     * 添加 维修详情表
     *
     * @param tbMaintenance 维修人员表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加维修人员表")
    @Parameters(value = {
            @Parameter(name = "id", description = "维修id"),

            @Parameter(name = "dormitoryId", description = "宿舍号"),

            @Parameter(name = "detail", description = "维修详情"),

            @Parameter(name = "createTime", description = "创建时间"),

            @Parameter(name = "student", description = "请求的学生,通过学生获取电话"),

            @Parameter(name = "status", description = "维修状态，0待支付，1待维修，2完成，3异常"),

            @Parameter(name = "maintenancePersonId", description = "维修人员id,通过维修人员表获取电话"),

            @Parameter(name = "typeId", description = "维修的类型id")
    })
    public Result<Boolean> save(@RequestBody MaintenanceEntity tbMaintenance) {
        return Result.success(tbMaintenanceService.save(tbMaintenance));
    }


    /**
     * 根据主键删除维修详情表
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除维修人员表")
    @Parameters(value = {
            @Parameter(name = "id", description = "维修id", required = true)
    })
    public Result<Boolean> remove(@PathVariable Serializable id) {
        return Result.success(tbMaintenanceService.removeById(id));
    }


    /**
     * 根据主键更新维修详情表
     *
     * @param tbMaintenance 维修人员表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新维修人员表")
    @Parameters(value = {
            @Parameter(name = "id", description = "维修id", required = true),

            @Parameter(name = "dormitoryId", description = "宿舍号"),

            @Parameter(name = "detail", description = "维修详情"),

            @Parameter(name = "createTime", description = "创建时间"),

            @Parameter(name = "student", description = "请求的学生,通过学生获取电话"),

            @Parameter(name = "status", description = "维修状态，0待支付，1待维修，2完成，3异常"),

            @Parameter(name = "maintenancePersonId", description = "维修人员id,通过维修人员表获取电话"),

            @Parameter(name = "typeId", description = "维修的类型id")
    })
    public Result<Boolean> update(@RequestBody MaintenanceEntity tbMaintenance) {
        return Result.success(tbMaintenanceService.updateById(tbMaintenance));
    }


    /**
     * 查询所有维修详情表
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有维修人员表")
    public Result<List<MaintenanceEntity>> list() {
        return Result.success(tbMaintenanceService.list());
    }


    /**
     * 根据维修详情表主键获取详细信息。
     *
     * @param id tbMaintenance主键
     * @return 维修人员表详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据维修人员表主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "维修id", required = true)
    })
    public Result<MaintenanceEntity> getInfo(@PathVariable Serializable id) {
        return Result.success(tbMaintenanceService.getById(id));
    }


    /**
     * 分页查询维修详情表
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询维修人员表")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Result<Page<MaintenanceEntity>> page(Page<MaintenanceEntity> page) {
        return Result.success(tbMaintenanceService.page(page));
    }
}
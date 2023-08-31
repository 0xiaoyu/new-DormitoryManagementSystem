package com.yu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.annotation.Resource;
import com.yu.service.SugTextService;
import com.yu.model.entity.SugTextEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

import com.yu.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 建议内容 控制层。
 *
 * @author yu
 * @since 1.0
 */
@RestController
@RequestMapping("/sugText")
@Tag(name = "建议内容控制层")
public class SugTextController {

    @Resource
    private SugTextService sugTextService;

    /**
     * 添加 建议内容
     *
     * @param sugText 建议内容
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加建议内容")
    @Parameters(value = {
            @Parameter(name = "id", description = "id"),

            @Parameter(name = "sId", description = "建议箱id"),

            @Parameter(name = "sText", description = "内容"),

            @Parameter(name = "type", description = "0发起，1回复")
    })
    public Result<Boolean> save(@RequestBody SugTextEntity sugText) {
        return Result.success(sugTextService.save(sugText));
    }


    /**
     * 根据主键删除建议内容
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除建议内容")
    @Parameters(value = {
            @Parameter(name = "id", description = "id", required = true)
    })
    public Result<Boolean> remove(@PathVariable Serializable id) {
        return Result.success(sugTextService.removeById(id));
    }


    /**
     * 根据主键更新建议内容
     *
     * @param sugText 建议内容
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新建议内容")
    @Parameters(value = {
            @Parameter(name = "id", description = "id", required = true),

            @Parameter(name = "sId", description = "建议箱id"),

            @Parameter(name = "sText", description = "内容"),

            @Parameter(name = "type", description = "0发起，1回复")
    })
    public Result<Boolean> update(@RequestBody SugTextEntity sugText) {
        return Result.success(sugTextService.updateById(sugText));
    }


    /**
     * 查询所有建议内容
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有建议内容")
    public Result<List<SugTextEntity>> list() {
        return Result.success(sugTextService.list());
    }


    /**
     * 根据建议内容主键获取详细信息。
     *
     * @param id sugText主键
     * @return 建议内容详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据建议内容主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "id", required = true)
    })
    public Result<SugTextEntity> getInfo(@PathVariable Serializable id) {
        return Result.success(sugTextService.getById(id));
    }


    /**
     * 分页查询建议内容
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询建议内容")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Result<Page<SugTextEntity>> page(Page<SugTextEntity> page) {
        return Result.success(sugTextService.page(page));
    }
}
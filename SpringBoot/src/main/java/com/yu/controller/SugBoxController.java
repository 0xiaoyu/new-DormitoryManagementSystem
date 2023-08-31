package com.yu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.result.Result;
import com.yu.model.entity.SugBoxEntity;
import com.yu.service.SugBoxService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 建议箱，反馈箱 控制层。
 *
 * @author yu
 * @since 1.0
 */
@RestController
@RequestMapping("/sugBox")
@Tag(name = "建议箱，反馈箱控制层")
public class SugBoxController {

    @Resource
    private SugBoxService sugBoxService;

    /**
     * 添加 建议箱，反馈箱
     *
     * @param sugBox 建议箱，反馈箱
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加建议箱，反馈箱")
    @Parameters(value = {
            @Parameter(name = "id", description = ""),

            @Parameter(name = "studentId", description = "学生id"),

            @Parameter(name = "createTime", description = "创建时间"),

            @Parameter(name = "status", description = "建议状态 0 未处理，1未恢复，2已回复,3已经处理"),

            @Parameter(name = "userId", description = "回复人员id"),

            @Parameter(name = "type", description = "建议类型是否匿名")
    })
    public Result<Boolean> save(@RequestBody SugBoxEntity sugBox) {
        return Result.success(sugBoxService.save(sugBox));
    }


    /**
     * 根据主键删除建议箱，反馈箱
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除建议箱，反馈箱")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true)
    })
    public Result<Boolean> remove(@PathVariable Serializable id) {
        return Result.success(sugBoxService.removeById(id));
    }


    /**
     * 根据主键更新建议箱，反馈箱
     *
     * @param sugBox 建议箱，反馈箱
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新建议箱，反馈箱")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true),

            @Parameter(name = "studentId", description = "学生id"),

            @Parameter(name = "createTime", description = "创建时间"),

            @Parameter(name = "status", description = "建议状态 0 未处理，1未恢复，2已回复,3已经处理"),

            @Parameter(name = "userId", description = "回复人员id"),

            @Parameter(name = "type", description = "建议类型是否匿名")
    })
    public Result<Boolean> update(@RequestBody SugBoxEntity sugBox) {
        return Result.success(sugBoxService.updateById(sugBox));
    }


    /**
     * 查询所有建议箱，反馈箱
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有建议箱，反馈箱")
    public Result<List<SugBoxEntity>> list() {
        return Result.success(sugBoxService.list());
    }


    /**
     * 根据建议箱，反馈箱主键获取详细信息。
     *
     * @param id sugBox主键
     * @return 建议箱，反馈箱详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据建议箱，反馈箱主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "", required = true)
    })
    public Result<SugBoxEntity> getInfo(@PathVariable Serializable id) {
        return Result.success(sugBoxService.getById(id));
    }


    /**
     * 分页查询建议箱，反馈箱
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询建议箱，反馈箱")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Result<Page<SugBoxEntity>> page(Page<SugBoxEntity> page) {
        return Result.success(sugBoxService.page(page));
    }
}
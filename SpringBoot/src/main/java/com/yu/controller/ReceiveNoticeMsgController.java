package com.yu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.base.BasePageQuery;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.model.entity.ReceiveNoticeMsgEntity;
import com.yu.service.ReceiveNoticeMsgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 接收通知 控制层。
 *
 * @author yu
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/receiveNoticeMsg")
@RequiredArgsConstructor
@Tag(name = "接收通知控制层")
public class ReceiveNoticeMsgController {


    private final ReceiveNoticeMsgService service;


    @GetMapping("/{id}")
    @Operation(summary = "根据用户id获取接收通知")
    @Parameters(value = {
            @Parameter(name = "id", description = "用户id", required = true)
    })
    public PageResult<ReceiveNoticeMsgEntity> getByUserId(
            @PathVariable Long id, BasePageQuery query) {
        Page<ReceiveNoticeMsgEntity> page = service.lambdaQuery().eq(ReceiveNoticeMsgEntity::getReceiveId, id)
                .page(query.getPage());
        List<ReceiveNoticeMsgEntity> list = page.getRecords().stream().map(item->
            ReceiveNoticeMsgEntity.builder().id(item.getId()).rStatus(1).build()
        ).toList();
        service.updateBatchById(list);
        return PageResult.success(page);
    }

    @GetMapping("count/noRead/{id}")
    @Operation(summary = "根据用户id获取未读通知数量")
    @Parameters(value = {
            @Parameter(name = "id", description = "用户id", required = true)
    })
    public Result<Long> getNoReadCount(@PathVariable Long id) {
        return Result.success(service.lambdaQuery().eq(ReceiveNoticeMsgEntity::getReceiveId, id)
                .eq(ReceiveNoticeMsgEntity::getRStatus, 0).count());
    }


    /**
     * 添加 接收通知
     *
     * @param receiveNoticeMsg 接收通知
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加接收通知")
    @Parameters(value = {
            @Parameter(name = "id", description = "接收通知id"),

            @Parameter(name = "noticeId", description = "接收的通知id"),

            @Parameter(name = "receiveId", description = "接收者id"),

            @Parameter(name = "rStatus", description = "接收状态,0未读，1已读")
    })
    public Result<Boolean> save(@RequestBody ReceiveNoticeMsgEntity receiveNoticeMsg) {
        return Result.success(service.save(receiveNoticeMsg));
    }


    /**
     * 根据主键删除接收通知
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除接收通知")
    @Parameters(value = {
            @Parameter(name = "id", description = "接收通知id", required = true)
    })
    public Result<Boolean> remove(@PathVariable Serializable id) {
        return Result.success(service.removeById(id));
    }


    /**
     * 根据主键更新接收通知
     *
     * @param receiveNoticeMsg 接收通知
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新接收通知")
    @Parameters(value = {
            @Parameter(name = "id", description = "接收通知id", required = true),

            @Parameter(name = "noticeId", description = "接收的通知id"),

            @Parameter(name = "receiveId", description = "接收者id"),

            @Parameter(name = "rStatus", description = "接收状态,0未读，1已读")
    })
    public Result<Boolean> update(@RequestBody ReceiveNoticeMsgEntity receiveNoticeMsg) {
        return Result.success(service.updateById(receiveNoticeMsg));
    }


    /**
     * 查询所有接收通知
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有接收通知")
    public Result<List<ReceiveNoticeMsgEntity>> list() {
        return Result.success(service.list());
    }


    /**
     * 根据接收通知主键获取详细信息。
     *
     * @param id receiveNoticeMsg主键
     * @return 接收通知详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据接收通知主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "接收通知id", required = true)
    })
    public Result<ReceiveNoticeMsgEntity> getInfo(@PathVariable Serializable id) {
        return Result.success(service.getById(id));
    }


    /**
     * 分页查询接收通知
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询接收通知")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Result<Page<ReceiveNoticeMsgEntity>> page(Page<ReceiveNoticeMsgEntity> page) {
        return Result.success(service.page(page));
    }
}
package com.yu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.result.Result;
import com.yu.model.entity.SenderNoticeMsgEntity;
import com.yu.service.SenderNoticeMsgService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 通知消息 控制层。
 *
 * @author yu
 * @since 1.0
 */
@RestController
@RequestMapping("/senderNoticeMsg")
@Tag(name = "通知消息控制层")
@RequiredArgsConstructor
public class SenderNoticeMsgController {

    private final SenderNoticeMsgService service;
    private final SimpMessagingTemplate messagingTemplate;


    public record NoticeStudent(Long sendId,Long studentId,Long buildId,String content){}
    @PostMapping("/student")
    @Operation(summary = "发送通知给学生")
    public Result<Boolean> sendToStudent(@RequestBody NoticeStudent student) {
        if (student.studentId!=null){
            messagingTemplate.convertAndSendToUser(String.valueOf(student.studentId), "/message", student.content);
            service.save(SenderNoticeMsgEntity.builder()
                    .senderId(student.sendId).nMsg(student.content)
                    .nType(1).build());
        }
        return Result.success(receiveNoticeMsgService.sendToStudent(receiveNoticeMsg));
    }

    /**
     * 添加 通知消息
     *
     * @param senderNoticeMsg 通知消息
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/save")
    @Operation(summary = "添加通知消息")
    @Parameters(value = {
            @Parameter(name = "id", description = "通知id"),

            @Parameter(name = "senderId", description = "发送者id，在tb_user表内的id"),

            @Parameter(name = "nMsg", description = "通知内容"),

            @Parameter(name = "nType", description = "通知类型")
    })
    public Result<Boolean> save(@RequestBody SenderNoticeMsgEntity senderNoticeMsg) {
        return Result.success(service.save(senderNoticeMsg));
    }


    /**
     * 根据主键删除通知消息
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("/remove/{id}")
    @Operation(summary = "根据主键删除通知消息")
    @Parameters(value = {
            @Parameter(name = "id", description = "通知id", required = true)
    })
    public Result<Boolean> remove(@PathVariable Serializable id) {
        return Result.success(service.removeById(id));
    }


    /**
     * 根据主键更新通知消息
     *
     * @param senderNoticeMsg 通知消息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("/update")
    @Operation(summary = "根据主键更新通知消息")
    @Parameters(value = {
            @Parameter(name = "id", description = "通知id", required = true),

            @Parameter(name = "senderId", description = "发送者id，在tb_user表内的id"),

            @Parameter(name = "nMsg", description = "通知内容"),

            @Parameter(name = "nType", description = "通知类型")
    })
    public Result<Boolean> update(@RequestBody SenderNoticeMsgEntity senderNoticeMsg) {
        return Result.success(service.updateById(senderNoticeMsg));
    }


    /**
     * 查询所有通知消息
     *
     * @return 所有数据
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有通知消息")
    public Result<List<SenderNoticeMsgEntity>> list() {
        return Result.success(service.list());
    }


    /**
     * 根据通知消息主键获取详细信息。
     *
     * @param id senderNoticeMsg主键
     * @return 通知消息详情
     */
    @GetMapping("/getInfo/{id}")
    @Operation(summary = "根据通知消息主键获取详细信息")
    @Parameters(value = {
            @Parameter(name = "id", description = "通知id", required = true)
    })
    public Result<SenderNoticeMsgEntity> getInfo(@PathVariable Serializable id) {
        return Result.success(service.getById(id));
    }


    /**
     * 分页查询通知消息
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询通知消息")
    @Parameters(value = {
            @Parameter(name = "pageNumber", description = "页码", required = true),
            @Parameter(name = "pageSize", description = "每页大小", required = true)
    })
    public Result<Page<SenderNoticeMsgEntity>> page(Page<SenderNoticeMsgEntity> page) {
        return Result.success(service.page(page));
    }
}
package com.yu.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.base.BasePageQuery;
import com.yu.common.enums.NoticeEnum;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.model.entity.ReceiveNoticeMsgEntity;
import com.yu.model.entity.SenderNoticeMsgEntity;
import com.yu.model.vo.NoticeVo;
import com.yu.service.DormitoryService;
import com.yu.service.ReceiveNoticeMsgService;
import com.yu.service.SenderNoticeMsgService;
import com.yu.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 接收通知 控制层。
 *
 * @author yu
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/notice")
@RequiredArgsConstructor
@Tag(name = "接收通知控制层")
public class NoticeController {

    private final SenderNoticeMsgService senderService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ReceiveNoticeMsgService receiveService;
    private final DormitoryService dormitoryService;
    private final SysUserService userService;


    public record NoticeStudent(Long sendId, String studentId,
                                String buildId, String content, NoticeEnum type
    ) {
    }

    /**
     * 发送通知给学生
     * 1.记录发送内容和发送者
     * 2，记录接收者
     * 3. webSocket发送
     *
     * @param notice 发送通知给学生
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("/student")
    @Operation(summary = "发送通知给学生")
    @Parameters(value = {
            @Parameter(name = "sendId", description = "发送者id，在tb_user表内的id", required = true),
            @Parameter(name = "studentId", description = "学生id，多个用逗号隔开"),
            @Parameter(name = "buildId", description = "楼栋id，多个用逗号隔开"),
            @Parameter(name = "content", description = "通知内容", required = true),
            @Parameter(name = "type", description = "通知类型", required = true)
    })
    @PreAuthorize("@security.hasPerm('notice:send:student')")
    public Result<Boolean> sendToStudent(@RequestBody NoticeStudent notice) {
        SenderNoticeMsgEntity msg = SenderNoticeMsgEntity.builder()
                .senderId(notice.sendId).msg(notice.content).build();
        if (StrUtil.isBlank(notice.studentId) && StrUtil.isBlank(notice.buildId)) {
            return Result.success(false);
        }
        // 1.记录发送内容和发送者
        if (StrUtil.isNotBlank(notice.studentId)) {
            msg.setType(NoticeEnum.PRIVATE_NOTICE);
            senderService.save(msg);
            Set<Long> ids = new HashSet<>(userService.getAllSysUserIdByStudentIds(notice.studentId.split(",")));
            List<ReceiveNoticeMsgEntity> receive = ids.stream().map(id -> {
                messagingTemplate.convertAndSendToUser(String.valueOf(id), "/notice/student", notice.content);
                return ReceiveNoticeMsgEntity.builder()
                        .receiveId(id).noticeId(msg.getId()).build();
            }).toList();
            receiveService.saveBatch(receive);
        } else {
            if (notice.buildId.equals("all")) {
                messagingTemplate.convertAndSend("/notice/all", notice.content);
                msg.setType(NoticeEnum.GROUP_NOTICE);
                senderService.save(msg);
                receiveService.save(ReceiveNoticeMsgEntity.builder()
                        .receiveId(0L).noticeId(msg.getId()).build());
            } else {
                msg.setType(NoticeEnum.DORMITORY_NOTICE);
                senderService.save(msg);
                List<ReceiveNoticeMsgEntity> list = Arrays.stream(notice.buildId.split(",")).map(id -> {
                    messagingTemplate.convertAndSendToUser(id, "/notice/build", notice.content);
                    return ReceiveNoticeMsgEntity.builder()
                            .receiveId(Long.valueOf(id)).noticeId(msg.getId()).build();
                }).toList();
                receiveService.saveBatch(list);
            }
        }
        return Result.success(true);
    }

    @Operation(summary = "获取通知类型", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/noticeType")
    public Result<NoticeEnum[]> getNoticeType() {
        return Result.success(NoticeEnum.values());
    }


    @GetMapping("/list/{id}")
    @Operation(summary = "根据用户id获取接收通知", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(value = {
            @Parameter(name = "id", description = "用户id", required = true)
    })
    public PageResult<NoticeVo> getByUserId(
            @PathVariable Long id, BasePageQuery query) {
        Page<NoticeVo> page = receiveService.getNoticeList(query.getPage(),id);
        return PageResult.success(page);
    }

    @GetMapping("count/noRead/{id}")
    @Operation(summary = "根据用户id获取未读通知数量", security = {@SecurityRequirement(name = "Authorization")})
    @Parameters(value = {
            @Parameter(name = "id", description = "用户id", required = true)
    })
    public Result<Long> getNoReadCount(@PathVariable Long id) {
        return Result.success(receiveService.getNoReadCount(id));
    }

}
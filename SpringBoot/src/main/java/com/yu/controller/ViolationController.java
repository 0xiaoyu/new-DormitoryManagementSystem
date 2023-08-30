package com.yu.controller;

import com.yu.common.result.PageResult;
import com.yu.model.query.ViolationLogPageQuery;
import com.yu.model.vo.ViolationLogPageVo;
import com.yu.service.ViolationLogService;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("violation")
@Tag(name = "11.违规控制", description = "")
public class ViolationController {

    @Resource
    private ViolationLogService service;

    @Schema(description = "日志列表")
    @GetMapping
    public PageResult<List<ViolationLogPageVo>> getPage(ViolationLogPageQuery query){
        return PageResult.success(service.getLogPage(query));
    }

}

package com.yu.controller;

import com.yu.common.result.PageResult;
import com.yu.model.query.ViolationLogPageQuery;
import com.yu.model.vo.ViolationLogPageVo;
import com.yu.service.ViolationLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/violationLog")
@Tag(name = "11.违规控制", description = "")
public class ViolationController {

    @Resource
    private ViolationLogService service;

    @Operation(description = "日志列表",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping
    public PageResult<ViolationLogPageVo> getPage(@ParameterObject ViolationLogPageQuery query){
        return PageResult.success(service.getLogPage(query));
    }

}

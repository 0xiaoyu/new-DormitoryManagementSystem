package com.yu.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yu.common.model.Option;
import com.yu.common.result.Result;
import com.yu.model.entity.Building;
import com.yu.service.BuildingService;
import com.yu.service.SysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/building")
@Tag(name = "09.楼栋接口")
public class BuildingController {

    @Resource
    private BuildingService service;
    @Resource
    private SysDictService sysDictService;

    @GetMapping("/option")
    @Operation(summary = "楼栋下拉列表", security = {@SecurityRequirement(name = "Authorization")})
    public Result<List<Option<Long>>> getOption(@ParameterObject Building building) {
        return Result.success(service.list(Wrappers.lambdaQuery(building)
                        .select(Building::getId, Building::getBuildName))
                .stream().map(item -> new Option<Long>(item.getId(), item.getBuildName())).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "楼栋详情", security = {@SecurityRequirement(name = "Authorization")})
    public Result<Building> get(@ParameterObject Building building) {
        return Result.success(service.getOne(Wrappers.lambdaQuery(building)));
    }
}

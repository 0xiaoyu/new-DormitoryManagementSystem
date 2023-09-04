package com.yu.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yu.common.model.BmMarker;
import com.yu.common.model.Option;
import com.yu.common.result.Result;
import com.yu.model.entity.Building;
import com.yu.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/building")
@Tag(name = "09.楼栋接口")
public class BuildingController {

    @Resource
    private BuildingService service;

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
    @GetMapping("/location")
    @Operation(summary = "查询所有宿舍的位置",security = {@SecurityRequirement(name = "Authorization")})
    public Result<List<BmMarker>> getLocation(){
        return Result.success(service.list().stream().map(BmMarker::new).toList());
    }

    @PostMapping
    @Operation(description = "添加楼层",security = {@SecurityRequirement(name = "Authorization")})
    public Result<Boolean> saveOrUpdateBuild(@RequestBody Building building){
        return Result.judge(service.saveOrUpdate(building));
    }

    @Operation(description = "楼层列表")
    @GetMapping
    public Result<List<Building>> getList(@ParameterObject Building building){
        String name = building.getBuildName();
        building.setBuildName(null);
        return Result.success(service.list(Wrappers.lambdaQuery(building)
                .like(StringUtils.hasText(name),Building::getBuildName,name)));
    }

    @Operation(description = "删除楼层")
    @DeleteMapping("/{ids}")
    public Result<Boolean> deleteBuild(@PathVariable String ids){
        return Result.judge(service.removeByIds(Arrays.asList(ids.split(","))));
    }
}

package com.yu.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yu.common.model.BmMarker;
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
    public Result<List<Option<String>>> getOption(@ParameterObject Building building){
        List<Option<String>> options = sysDictService.listDictOptions("building");
        for (Option<String> o : options) {
            o.setChildren(service.list(Wrappers.lambdaQuery(Building.class)
                            .eq(Building::getBuildName, o.getValue())
                            .select(Building::getId, Building::getBuildName))
                    .stream().map(item -> new Option(item.getId(), item.getBuildName())).toList());
        }
        return Result.success(options);
    }

    @GetMapping("/location")
    @Operation(summary = "查询所有宿舍的位置",security = {@SecurityRequirement(name = "Authorization")})
    public Result<List<BmMarker>> getLocation(){
        return Result.success(service.list().stream().map(BmMarker::new).toList());
    }

}

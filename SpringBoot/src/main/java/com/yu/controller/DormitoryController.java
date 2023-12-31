package com.yu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yu.common.enums.PayTypeEnum;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.model.entity.Dormitory;
import com.yu.model.entity.PayLogEntity;
import com.yu.model.entity.SysDict;
import com.yu.model.query.DormitoryPageQuery;
import com.yu.service.DormitoryService;
import com.yu.service.PayLogService;
import com.yu.service.SysDictService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dormitory")
@RequiredArgsConstructor
@Tag(name = "08.宿舍楼接口", description = "宿舍楼相关接口")
public class DormitoryController {

    private final DormitoryService dormitoryService;
    private final PayLogService payLogService;
    private final SysDictService dictService;

    @GetMapping("/page")
    @Operation(summary = "宿舍楼分页", security = {@SecurityRequirement(name = "Authorization")})
    public PageResult<Dormitory> page(@ParameterObject DormitoryPageQuery queryParams) {
        IPage<Dormitory> result = dormitoryService.getPage(queryParams);
        return PageResult.success(result);
    }


    @PatchMapping("/{id}/electricity")
    @Operation(summary = "物联网同步接口,修改电费", security = {@SecurityRequirement(name = "Authorization")})
    @PreAuthorize("@security.hasPerm('dormitory:update:electricity')")
    public Result<Boolean> updateElectricity(@Parameter(description = "宿舍id") @PathVariable Long id, @Parameter(description = "电费") @RequestParam Double electricity) {
        boolean b = dormitoryService.update(Wrappers.lambdaUpdate(Dormitory.class)
                .eq(Dormitory::getId, id)
                .set(Dormitory::getElectricity, electricity)
                .set(Dormitory::getEStatus, electricity > 0 ? 0 : 1));
        return Result.judge(b);
    }

    @PatchMapping("/{id}/water")
    @Operation(summary = "物联网同步接口,修改水费", security = {@SecurityRequirement(name = "Authorization")})
    @PreAuthorize("@security.hasPerm('dormitory:update:water')")
    public Result<Boolean> updateWater(@Parameter(description = "宿舍id") @PathVariable Long id, @Parameter(description = "水费") @RequestParam Double water) {
        boolean b = dormitoryService.update(Wrappers.lambdaUpdate(Dormitory.class)
                .eq(Dormitory::getId, id)
                .set(Dormitory::getWater, water)
                .set(Dormitory::getWStatus, water > 0 ? 0 : 1));
        return Result.judge(b);
    }

    @Operation(summary = "更新寝室信息")
    @PatchMapping
    public Result<Boolean> update(@RequestBody Dormitory dormitoryPage) {
        return Result.judge(dormitoryService.updateById(dormitoryPage));
    }

    @PostMapping
    public Result<PayLogEntity> generateOrder(@RequestBody PayLogEntity payLog) {
        payLog.setStatus(0);
        payLogService.save(payLog);
        return Result.success(payLog);
    }

    @PatchMapping("/{id}/payElectricity")
    public Result<Boolean> payElectricity(@Parameter(description = "宿舍id") @PathVariable Long id,
                                          @Parameter(description = "缴费人id") @RequestParam Long userId,
                                          @Parameter(description = "缴纳金额") @RequestParam Double money
    ) {
        String value = dictService.getOne(Wrappers.lambdaQuery(SysDict.class)
                .select(SysDict::getValue).eq(SysDict::getName, "电费比例")).getValue();
        Double payElectricity = Double.valueOf(value);
        boolean b = dormitoryService.update(Wrappers.<Dormitory>lambdaUpdate()
                .setSql("electricity = electricity + %f".formatted(money)).eq(Dormitory::getId, id));
        if (!b)
            return Result.failed("缴费失败，请联系管理员");
        payLogService.save(PayLogEntity.builder()
                .dormitoryId(id).amount(money).userId(userId).type(PayTypeEnum.ELECTRICITY).status(1)
                .build());
        return Result.success();
    }


}

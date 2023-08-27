package com.yu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.annotation.PreventDuplicateSubmit;
import com.yu.common.model.Option;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.model.entity.SysDict;
import com.yu.model.entity.SysDictType;
import com.yu.model.query.DictPageQuery;
import com.yu.model.query.DictTypePageQuery;
import com.yu.model.vo.DictPageVO;
import com.yu.service.SysDictService;
import com.yu.service.SysDictTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "06.字典接口")
@RestController
@RequestMapping("/api/v1/dict")
public class SysDictController {

    @Resource
    private SysDictService dictService;

    @Resource
    private SysDictTypeService dictTypeService;

    @Operation(summary = "字典分页列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/page")
    public PageResult<DictPageVO> getDictPage(
            @ParameterObject DictPageQuery queryParams
    ) {
        Page<DictPageVO> result = dictService.getDictPage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "字典数据表单数据", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}/form")
    public Result<SysDict> getDictForm(
            @Parameter(description ="字典ID") @PathVariable Long id
    ) {
        return Result.success(dictService.getDictForm(id));
    }

    @Operation(summary = "新增字典", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping
    @PreAuthorize("@security.hasPerm('sys:dict:add')")
    @PreventDuplicateSubmit
    public Result<Boolean> saveDict(
            @RequestBody SysDict DictForm
    ) {
        boolean result = dictService.saveDict(DictForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改字典", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{id}")
    @PreAuthorize("@security.hasPerm('sys:dict:edit')")
    public Result<Boolean> updateDict(
            @PathVariable Long id,
            @RequestBody SysDict DictForm
    ) {
        boolean status = dictService.updateDict(id, DictForm);
        return Result.judge(status);
    }

    @Operation(summary = "删除字典", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{ids}")
    @PreAuthorize("@security.hasPerm('sys:dict:delete')")
    public Result<Boolean> deleteDict(
            @Parameter(description ="字典ID，多个以英文逗号(,)拼接") @PathVariable String ids
    ) {
        boolean result = dictService.deleteDict(ids);
        return Result.judge(result);
    }


    @Operation(summary = "字典下拉列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{typeCode}/options")
    public Result<List<Option<String>>> listDictOptions(
            @Parameter(description ="字典类型编码") @PathVariable String typeCode
    ) {
        List<Option<String>> list = dictService.listDictOptions(typeCode);
        return Result.success(list);
    }


    /*----------------------------------------------------*/
    @Operation(summary = "字典类型分页列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/types/page")
    public PageResult<SysDictType> getDictTypePage(
            @ParameterObject DictTypePageQuery queryParams
    ) {
        Page<SysDictType> result = dictTypeService.getDictTypePage(queryParams);
        return PageResult.success(result);
    }

    @Operation(summary = "字典类型表单数据", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/types/{id}/form")
    public Result<SysDictType> getDictTypeForm(
            @Parameter(description ="字典ID") @PathVariable Long id
    ) {
        SysDictType dictTypeForm = dictTypeService.getDictTypeForm(id);
        return Result.success(dictTypeForm);
    }

    @Operation(summary = "新增字典类型", security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping("/types")
    @PreAuthorize("@security.hasPerm('sys:dict_type:add')")
    @PreventDuplicateSubmit
    public Result<Boolean> saveDictType(@RequestBody SysDictType dictTypeForm) {
        boolean result = dictTypeService.saveDictType(dictTypeForm);
        return Result.judge(result);
    }

    @Operation(summary = "修改字典类型", security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/types/{id}")
    @PreAuthorize("@security.hasPerm('sys:dict_type:edit')")
    public Result<Boolean> updateDictType(@PathVariable Long id, @RequestBody SysDictType dictTypeForm) {
        boolean status = dictTypeService.updateDictType(id, dictTypeForm);
        return Result.judge(status);
    }

    @Operation(summary = "删除字典类型", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/types/{ids}")
    @PreAuthorize("@security.hasPerm('sys:dict_type:delete')")
    public Result<Boolean> deleteDictTypes(
            @Parameter(description ="字典类型ID，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = dictTypeService.deleteDictTypes(ids);
        return Result.judge(result);
    }

}

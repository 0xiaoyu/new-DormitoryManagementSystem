package com.yu.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yu.common.annotation.PreventDuplicateSubmit;
import com.yu.common.model.Option;
import com.yu.common.result.PageResult;
import com.yu.common.result.Result;
import com.yu.model.entity.SysRole;
import com.yu.model.query.RolePageQuery;
import com.yu.service.SysRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "03.角色接口")
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class SysRoleController {

    private final SysRoleService roleService;

    @Operation(summary = "角色分页列表",security = {@SecurityRequirement(name = "Authorization")} )
    @GetMapping("/page")
    public PageResult<SysRole> getRolePage(
            @ParameterObject RolePageQuery queryParams
    ) {
        return PageResult.success(roleService.getRolePage(queryParams));
    }

    @Operation(summary = "角色下拉列表",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/options")
    public Result<List<Option<Long>>> listRoleOptions() {
        return Result.success(roleService.listRoleOptions());
    }
 
    @Operation(summary = "新增角色",security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping
    @PreAuthorize("@ss.hasPerm('sys:role:add')")
    @PreventDuplicateSubmit
    public Result<Boolean> addRole(@Valid @RequestBody SysRole roleForm) {
        boolean result = roleService.saveRole(roleForm);
        return Result.judge(result);
    }

    @Operation(summary = "角色表单数据",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{roleId}/form")
    public Result<SysRole> getRoleForm(
            @Parameter(description ="角色ID") @PathVariable Long roleId
    ) {
        return Result.success(roleService.getRoleForm(roleId));
    }

    @Operation(summary = "修改角色",security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping(value = "/{id}")
    @PreAuthorize("@ss.hasPerm('sys:role:edit')")
    public Result<Boolean> updateRole(@Valid @RequestBody SysRole roleForm) {
        return Result.judge(roleService.saveRole(roleForm));
    }

    @Operation(summary = "删除角色",security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{ids}")
    @PreAuthorize("@ss.hasPerm('sys:role:delete')")
    public Result<Boolean> deleteRoles(
            @Parameter(description ="删除角色，多个以英文逗号(,)分割") @PathVariable String ids
    ) {
        boolean result = roleService.deleteRoles(ids);
        return Result.judge(result);
    }

    @Operation(summary = "修改角色状态",security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping(value = "/{roleId}/status")
    public Result<Boolean> updateRoleStatus(
            @Parameter(description ="角色ID") @PathVariable Long roleId,
            @Parameter(description ="状态(1:启用;0:禁用)") @RequestParam Integer status
    ) {
        boolean update = roleService.update(new LambdaUpdateWrapper<SysRole>()
                .eq(SysRole::getId, roleId)
                .set(SysRole::getStatus, status));
        return Result.judge(update);
    }

    @Operation(summary = "获取角色的菜单ID集合",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{roleId}/menuIds")
    public Result<List<Long>> getRoleMenuIds(
            @Parameter(description ="角色ID") @PathVariable Long roleId
    ) {
        return Result.success(roleService.getRoleMenuIds(roleId));
    }

    @Operation(summary = "分配菜单权限给角色",security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping("/{roleId}/menus")
    public Result<Boolean> updateRoleMenus(
            @PathVariable Long roleId,
            @RequestBody List<Long> menuIds
    ) {
        return Result.judge(roleService.updateRoleMenus(roleId,menuIds));
    }
}

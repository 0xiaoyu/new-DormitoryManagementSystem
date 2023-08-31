package com.yu.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yu.common.annotation.PreventDuplicateSubmit;
import com.yu.common.enums.MenuTypeEnum;
import com.yu.common.model.Option;
import com.yu.common.result.Result;
import com.yu.model.entity.SysMenu;
import com.yu.model.form.MenuForm;
import com.yu.model.vo.MenuVO;
import com.yu.model.vo.RouteVO;
import com.yu.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "04.菜单接口")
@RestController
@RequestMapping("/api/v1/menus")
@Slf4j
public class SysMenuController {

    @Resource
    private SysMenuService menuService;

    @Operation(summary = "路由列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/routes")
    public Result<List<RouteVO>> listRoutes() {
        List<RouteVO> routeList = menuService.listRoutes();
        return Result.success(routeList);
    }

    @Operation(summary = "菜单列表", security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping
    public Result<List<MenuVO>> listMenus(
            @Schema(description = "关键字(菜单名称)") String keywords,
            @Schema(description = "菜单状态(1-正常；0-停用)") Integer status
    ) {
        List<MenuVO> menuList = menuService.listMenus(keywords, status);
        return Result.success(menuList);
    }


    @Operation(summary = "菜单下拉列表",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/options")
    public Result<List<Option>> listMenuOptions() {
        List<Option> menus = menuService.listMenuOptions();
        return Result.success(menus);
    }

    @Operation(summary = "菜单详细数据",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/{id}/form")
    public Result<SysMenu> getMenuForm(
            @Parameter(description =  "菜单ID") @PathVariable Long id
    ) {
        return Result.success(menuService.getById(id));
    }
    @Operation(summary = "新增菜单",security = {@SecurityRequirement(name = "Authorization")})
    @PostMapping
    @PreAuthorize("@security.hasPerm('sys:menu:add')")
    @PreventDuplicateSubmit
    @CacheEvict(cacheNames = "system", key = "'routes'")
    public Result<Boolean> addMenu(@RequestBody MenuForm menuForm) {
        return Result.judge( menuService.saveMenu(menuForm));
    }

    @Operation(summary = "修改菜单",security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping(value = "/{id}")
    @PreAuthorize("@security.hasPerm('sys:menu:edit')")
    @CacheEvict(cacheNames = "system", key = "'routes'")
    public Result<Boolean> updateMenu(
            @RequestBody MenuForm menuForm
    ) {
        if (menuForm.getType() != MenuTypeEnum.BUTTON){
            menuForm.setPerm(null);
        }
        boolean result = menuService.saveMenu(menuForm);
        return Result.judge(result);
    }

    @Operation(summary = "删除菜单", security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping("/{id}")
    @PreAuthorize("@security.hasPerm('sys:menu:delete')")
    @CacheEvict(cacheNames = "system", key = "'routes'")
    public Result<Boolean> deleteMenu(
            @Parameter(description = "菜单ID，多个以英文(,)分割") @PathVariable("id") Long id
    ) {
        boolean result = menuService.remove(Wrappers.lambdaQuery(SysMenu.class)
                .eq(SysMenu::getId, id)
                .or()
                .apply("CONCAT (',',tree_path,',') LIKE CONCAT('%,',{0},',%')", id));
        return Result.judge(result);
    }


}

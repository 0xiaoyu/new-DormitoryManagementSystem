package com.yu.controller;

import com.yu.common.result.Result;
import com.yu.model.vo.RouteVO;
import com.yu.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "04.菜单接口")
@RestController
@RequestMapping("/menus")
@Slf4j
public class SysMenuController {

    @Resource
    private SysMenuService menuService;

    @Operation(summary = "路由列表",security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping("/routes")
    public Result<List<RouteVO>> listRoutes() {
        List<RouteVO> routeList = menuService.listRoutes();
        return Result.success(routeList);
    }


}

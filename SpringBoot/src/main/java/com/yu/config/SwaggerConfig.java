package com.yu.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger 配置
 * <p>
 * Spring Doc FAQ: https://springdoc.org/#faq
 *
 * @author zay
 * @since 2023/2/17
 */
@Configuration
public class SwaggerConfig {

    /**
     * 接口信息
     */
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("Authorization",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer").bearerFormat("JWT")
                        )
                )
                .info(new Info()
                        .title("xiao-yu-Dormitory 接口文档")
                        .version("1.0.0")
                        .description("接口文档")
                );
    }

    /**
     * 系统接口分组
     */
    @Bean
    public GroupedOpenApi systemApi() {
        String[] paths = {"/**"};
        String[] packagesToScan = {"com.yu.controller"};
        return GroupedOpenApi.builder()
                .group("系统接口")
                .packagesToScan(packagesToScan)
                .pathsToMatch(paths)
                .build();
    }


}

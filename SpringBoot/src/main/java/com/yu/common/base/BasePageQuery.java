package com.yu.common.base;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 基础分页请求对象
 *
 * @author haoxr
 * @since 2021/2/28
 */
@Data
@Schema
public class BasePageQuery {

    @Schema(description = "页码", example = "1", required = true)
    private int pageNum = 1;

    @Schema(description = "每页记录数", example = "10", required = true)
    private int pageSize = 10;

    public <T> Page<T> getPage(){
        return Page.of(pageNum, pageSize);
    }
}

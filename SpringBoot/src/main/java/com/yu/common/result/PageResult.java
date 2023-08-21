package com.yu.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 分页响应结构体
 *
 * @author zay
 * @since 2023/08/20
 */
public record PageResult<T>(
        String code,
        Data<T> data,
        String msg
) implements Serializable {


    public static <T> PageResult<T> success(IPage<T> page) {
        Data<T> data = new Data<T>(page.getRecords(), page.getTotal());
        return new PageResult<T>(
                ResultCode.SUCCESS.getCode(), data, ResultCode.SUCCESS.getMsg()
        );
    }

    public record Data<T>(List<T> list, long total) {
    }

}

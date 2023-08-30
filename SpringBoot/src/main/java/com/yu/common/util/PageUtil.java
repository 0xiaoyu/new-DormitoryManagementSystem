package com.yu.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.base.BasePageQuery;

public class PageUtil {

    public static Page create(BasePageQuery query){
        return new Page<>(query.getPageNum(),query.getPageSize());
    }

}

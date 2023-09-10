package com.yu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.entity.AccessLogEntity;
import com.yu.model.query.PassLogPageQuery;
import com.yu.model.vo.PassPageVo;

/**
 * 进入记录 映射层。
 *
 * @author yu
 * @since 1.0
 */

public interface AccessLogMapper extends BaseMapper<AccessLogEntity> {


    Page<PassPageVo> getPageQuery(Page<PassPageVo> page,
                                  Boolean s,
                                  Boolean o,
                                  PassLogPageQuery q);
}

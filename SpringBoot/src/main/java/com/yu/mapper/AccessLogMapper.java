package com.yu.mapper;

import com.yu.model.entity.AccessLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 进入记录 映射层。
 *
 * @author yu
 * @since 1.0
 */
@Mapper
public interface AccessLogMapper extends BaseMapper<AccessLogEntity> {


}

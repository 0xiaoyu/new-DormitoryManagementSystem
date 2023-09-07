package com.yu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.entity.MaintenanceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.model.query.MaintenPageQuery;
import com.yu.model.vo.MaintenPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 维修人员表 映射层。
 *
 * @author yu
 * @since 1.0
 */
@Mapper
public interface TbMaintenanceMapper extends BaseMapper<MaintenanceEntity> {


    Page<MaintenPageVo> getPageByCondition(Page<MaintenPageVo> page, @Param("query") MaintenPageQuery query);
}

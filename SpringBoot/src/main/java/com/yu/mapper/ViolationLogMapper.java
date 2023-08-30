package com.yu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.common.result.PageResult;
import com.yu.model.entity.ViolationLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.model.query.ViolationLogPageQuery;
import com.yu.model.vo.ViolationLogPageVo;

import java.util.List;

/**
* @author zay
* @description 针对表【violation_log(违规记录)】的数据库操作Mapper
* @since  2023-08-30 17:22:54
* @Entity com.yu.model.entity.ViolationLog
*/
public interface ViolationLogMapper extends BaseMapper<ViolationLog> {

    PageResult<List<ViolationLogPageVo>> getLogPage(Page page, ViolationLogPageQuery q);
}





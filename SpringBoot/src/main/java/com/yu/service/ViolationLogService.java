package com.yu.service;

import com.yu.common.result.PageResult;
import com.yu.model.entity.ViolationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.query.ViolationLogPageQuery;
import com.yu.model.vo.ViolationLogPageVo;

import java.util.List;

/**
* @author zay
* @description 针对表【violation_log(违规记录)】的数据库操作Service
* @since  2023-08-30 17:22:54
*/
public interface ViolationLogService extends IService<ViolationLog> {

    PageResult<List<ViolationLogPageVo>> getLogPage(ViolationLogPageQuery query);
}

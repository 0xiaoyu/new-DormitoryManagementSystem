package com.yu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.entity.ViolationLog;
import com.yu.model.query.ViolationLogPageQuery;
import com.yu.model.vo.ViolationLogPageVo;

/**
* @author zay
* @description 针对表【violation_log(违规记录)】的数据库操作Service
* @since  2023-08-30 17:22:54
*/
public interface ViolationLogService extends IService<ViolationLog> {

    Page<ViolationLogPageVo> getLogPage(ViolationLogPageQuery query);
}

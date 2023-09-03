package com.yu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.entity.AccessLogEntity;
import com.yu.model.query.PassLogPageQuery;
import com.yu.model.vo.PassPageVo;

/**
* @author zay
* @description 针对表【access_log(进入记录)】的数据库操作Service
* @since  2023-08-30 17:21:24
*/
public interface AccessLogService extends IService<AccessLogEntity> {

    Page<PassPageVo> getPageQuery(PassLogPageQuery query);
}

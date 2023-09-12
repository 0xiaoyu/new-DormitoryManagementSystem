package com.yu.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.util.PageUtil;
import com.yu.model.entity.ViolationLog;
import com.yu.model.query.ViolationLogPageQuery;
import com.yu.model.vo.ViolationLogPageVo;
import com.yu.service.ViolationLogService;
import com.yu.mapper.ViolationLogMapper;
import org.springframework.stereotype.Service;

/**
* @author zay
* @description 针对表【violation_log(违规记录)】的数据库操作Service实现
* @since  2023-08-30 17:22:54
*/
@Service
public class ViolationLogServiceImpl extends ServiceImpl<ViolationLogMapper, ViolationLog>
    implements ViolationLogService{

    @Override
    public Page<ViolationLogPageVo> getLogPage(ViolationLogPageQuery query) {
        Page page = PageUtil.create(query);
        return this.baseMapper.getLogPage(page,query);
    }
}





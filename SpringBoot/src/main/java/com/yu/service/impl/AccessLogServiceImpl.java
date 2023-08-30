package com.yu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.model.entity.AccessLog;
import com.yu.service.AccessLogService;
import com.yu.mapper.AccessLogMapper;
import org.springframework.stereotype.Service;

/**
* @author ymauser
* @description 针对表【access_log(进入记录)】的数据库操作Service实现
* @createDate 2023-08-30 17:21:24
*/
@Service
public class AccessLogServiceImpl extends ServiceImpl<AccessLogMapper, AccessLog>
    implements AccessLogService{

}





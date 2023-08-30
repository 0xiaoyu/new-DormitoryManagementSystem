package com.yu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.model.entity.SugText;
import com.yu.service.SugTextService;
import com.yu.mapper.SugTextMapper;
import org.springframework.stereotype.Service;

/**
* @author ymauser
* @description 针对表【sug_text(建议内容)】的数据库操作Service实现
* @createDate 2023-08-30 17:22:22
*/
@Service
public class SugTextServiceImpl extends ServiceImpl<SugTextMapper, SugText>
    implements SugTextService{

}





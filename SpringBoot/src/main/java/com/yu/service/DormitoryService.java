package com.yu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yu.model.entity.Dormitory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.query.DormitoryPageQuery;

/**
* @author za'y
* @description 针对表【tb_dormitory】的数据库操作Service
* @createDate 2023-08-27 21:57:58
*/
public interface DormitoryService extends IService<Dormitory> {

    IPage<Dormitory> getPage(DormitoryPageQuery queryParams);
}

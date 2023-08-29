package com.yu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.mapper.DormitoryMapper;
import com.yu.model.entity.Dormitory;
import com.yu.model.query.DormitoryPageQuery;
import com.yu.service.DormitoryService;
import org.springframework.stereotype.Service;

/**
 * @author za'y
 * @description 针对表【tb_dormitory】的数据库操作Service实现
 * @createDate 2023-08-27 21:57:58
 */
@Service
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory>
        implements DormitoryService {

    @Override
    public IPage<Dormitory> getPage(DormitoryPageQuery queryParams) {
        Page<Dormitory> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        return this.baseMapper.floorPage(page, queryParams);
    }
}





package com.yu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.entity.Dormitory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yu.model.query.DormitoryPageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author za'y
* @description 针对表【tb_dormitory】的数据库操作Mapper
* @createDate 2023-08-27 21:57:58
* @Entity com.yu.model.entity.Dormitory
*/
public interface DormitoryMapper extends BaseMapper<Dormitory> {

    IPage<Dormitory> floorPage(Page<Dormitory> page, DormitoryPageQuery q);

    List<Long> getSysIdsByBuildingId(@Param("list") String[] list);
}





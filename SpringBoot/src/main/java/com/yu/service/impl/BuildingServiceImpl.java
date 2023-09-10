package com.yu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.mapper.BuildingMapper;
import com.yu.model.entity.Building;
import com.yu.service.BuildingService;
import org.springframework.stereotype.Service;

/**
* @author za'y
* @description 针对表【d_building(宿舍楼栋)】的数据库操作Service实现
* @since  2023-08-27 21:57:58
*/
@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building>
    implements BuildingService{


}





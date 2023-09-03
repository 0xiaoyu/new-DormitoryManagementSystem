package com.yu.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.entity.UserEntity;
import com.yu.model.vo.DormitoryPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 人员表 映射层。
 *
 * @author yu
 * @since 2.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {


    Page<DormitoryPageVo> pageDormitory(Page<DormitoryPageVo> page,@Param("ew") LambdaQueryWrapper<UserEntity> lq);

    Page<DormitoryPageVo> pageRepair(Page<Object> page,@Param("ew") LambdaQueryWrapper<UserEntity> lq);
}

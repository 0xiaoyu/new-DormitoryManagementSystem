package com.yu.mapper;

import com.yu.model.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人员表 映射层。
 *
 * @author yu
 * @since 2.0
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {


}

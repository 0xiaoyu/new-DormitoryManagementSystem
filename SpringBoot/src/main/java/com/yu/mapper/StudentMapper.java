package com.yu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yu.model.entity.Student;
import com.yu.model.query.StudentPageQuery;
import com.yu.model.vo.StudentInfoVo;
import com.yu.model.vo.StudentPageVo;

/**
* @author ymauser
* 针对表【tb_student】的数据库操作Mapper
* @since  2023-08-24 16:19:44
* &#064;Entity  com.yu.model.entity.Student
 */
public interface StudentMapper extends BaseMapper<Student> {

    IPage<StudentPageVo> getPage(Page<StudentPageVo> page, StudentPageQuery queryParams);

    StudentInfoVo getStudentInfoAndV(String id);
}





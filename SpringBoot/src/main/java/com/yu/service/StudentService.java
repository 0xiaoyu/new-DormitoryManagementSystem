package com.yu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.model.entity.Student;
import com.yu.model.query.StudentPageQuery;
import com.yu.model.vo.StudentPageVo;

/**
* @author zay
* @since  2023-08-24 16:19:44
*/
public interface StudentService extends IService<Student> {

    IPage<StudentPageVo> getPage(StudentPageQuery queryParams);

    Boolean saveOrUpdateStudent(StudentPageVo student);
}

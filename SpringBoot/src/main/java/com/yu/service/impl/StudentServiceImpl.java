package com.yu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.model.entity.Student;
import com.yu.service.StudentService;
import com.yu.mapper.StudentMapper;
import org.springframework.stereotype.Service;

/**
* @author za'y
* @description 针对表【tb_student】的数据库操作Service实现
* @createDate 2023-08-22 23:22:21
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}





package com.yu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.mapper.StudentMapper;
import com.yu.model.entity.Student;
import com.yu.service.StudentService;
import org.springframework.stereotype.Service;

/**
* @author zay
* 针对表【tb_student】的数据库操作Service实现
* @since  2023-08-24 16:19:44
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
    implements StudentService{

}





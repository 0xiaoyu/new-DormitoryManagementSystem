package com.yu.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.yu.mapper.StudentMapper;
import com.yu.model.entity.Building;
import com.yu.model.entity.Dormitory;
import com.yu.model.entity.PayLogEntity;
import com.yu.model.entity.Student;
import com.yu.model.query.StudentPageQuery;
import com.yu.model.vo.StudentInfoVo;
import com.yu.model.vo.StudentPageVo;
import com.yu.service.BuildingService;
import com.yu.service.DormitoryService;
import com.yu.service.StudentService;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zay
 * 针对表【tb_student】的数据库操作Service实现
 * @since 2023-08-24 16:19:44
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {

    @Resource
    private DormitoryService dormitoryService;

    @Resource
    private BuildingService buildingService;

    @Override
    public IPage<StudentPageVo> getPage(StudentPageQuery queryParams) {
        Page<StudentPageVo> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        return this.baseMapper.getPage(page, queryParams);
    }

    @Override
    @Transactional
    public Boolean saveOrUpdateStudent(StudentPageVo student) {
        String buildName = student.getBuildName();
        Building build = buildingService.getOne(Wrappers.lambdaQuery(Building.class)
                .eq(Building::getBuildName, buildName)
                .select(Building::getId)
        );
        Assert.notNull(build, "楼栋不存在");
        String dormitoryNumber = student.getDormitoryNumber();
        Dormitory dormitory = dormitoryService.getOne(Wrappers.lambdaQuery(Dormitory.class)
                .eq(Dormitory::getBuildingId, build.getId())
                .eq(Dormitory::getDormitoryNumber, dormitoryNumber)
                .select(Dormitory::getId)
        );
        Assert.notNull(dormitory, "宿舍不存在");
        Student s = new Student();
        BeanUtils.copyProperties(student, s);
        s.setDormitoryId(dormitory.getId());
        return this.saveOrUpdate(s);
    }

    @Override
    public StudentInfoVo getStudentInfo(String id) {
        StudentInfoVo studentInfo = baseMapper.getStudentInfoAndV(id);
        studentInfo.setPaymentLogs(Db.lambdaQuery(PayLogEntity.class).eq(PayLogEntity::getUserId,id).list());
        return studentInfo;
    }
}





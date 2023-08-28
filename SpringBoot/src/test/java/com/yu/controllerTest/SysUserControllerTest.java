package com.yu.controllerTest;

import cn.hutool.core.lang.Assert;
import com.yu.common.result.Result;
import com.yu.controller.SysUserController;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SysUserControllerTest {
    @Resource
    private SysUserController controller;

    @Test
    public void saveStudentTest(){
        /*SysUserController.StudentUser studentUser = new SysUserController.StudentUser(
                "王1","张三","123456","1621@qq.com","/static/nomal.gif","1","19024521315"
        );
        Assert.equals(controller.saveStudent(studentUser,"123"), Result.failed("学生信息不匹配"));
        SysUserController.StudentUser studentUser1 = new SysUserController.StudentUser(
                "王","张三","123456","1621@qq.com","/static/nomal.gif","1","19024521315"
        );
        Assert.equals(controller.saveStudent(studentUser1,"Asd"), Result.success());*/

    }
}

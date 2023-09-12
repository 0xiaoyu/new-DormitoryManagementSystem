package com.yu.listener.easyexcel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yu.common.constant.SystemConstants;
import com.yu.common.enums.StatusEnum;
import com.yu.model.entity.SysRole;
import com.yu.model.entity.SysUser;
import com.yu.model.entity.SysUserRole;
import com.yu.model.vo.UserImportVO;
import com.yu.service.SysRoleService;
import com.yu.service.SysUserRoleService;
import com.yu.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户导入监听器
 * <p>
 * 最简单的读监听器： https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read
 *
 * @author zay
 * @since 2023/8/25
 */
@Slf4j
public class UserImportListener extends MyAnalysisEventListener<UserImportVO> {


    // 有效条数
    private int validCount;

    // 无效条数
    private int invalidCount;

    // 导入返回信息
    StringBuilder msg = new StringBuilder();

    private final SysUserService userService;

    private final PasswordEncoder passwordEncoder;


    private final SysRoleService roleService;

    private final SysUserRoleService userRoleService;

    public UserImportListener() {
        this.userService = SpringUtil.getBean(SysUserService.class);
        this.passwordEncoder = SpringUtil.getBean(PasswordEncoder.class);
        this.roleService = SpringUtil.getBean(SysRoleService.class);
        this.userRoleService = SpringUtil.getBean(SysUserRoleService.class);
    }

    /**
     * 每一条数据解析都会来调用
     * <p>
     * 1. 数据校验；全字段校验
     * 2. 数据持久化；
     *
     * @param userImportVO    一行数据，类似于 {@link AnalysisContext#readRowHolder()}
     * @param analysisContext
     */
    @Override
    public void invoke(UserImportVO userImportVO, AnalysisContext analysisContext) {
        log.info("解析到一条用户数据:{}", JSONUtil.toJsonStr(userImportVO));
        // 校验数据
        StringBuilder validationMsg = new StringBuilder();

        String username = userImportVO.getName();
        if (StrUtil.isBlank(username)) {
            validationMsg.append("用户名为空；");
        } else {
            long count = userService.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getName, username));
            if (count > 0) {
                validationMsg.append("用户名已存在；");
            }
        }

        String email = userImportVO.getEmail();
        if (StrUtil.isBlank(email)) {
            validationMsg.append("邮箱为空；");
        } else {
            if (!Validator.isEmail(email)) {
                validationMsg.append("邮箱格式不正确；");
            }
        }

        if (validationMsg.isEmpty()) {
            // 校验通过，持久化至数据库
            SysUser entity = new SysUser();
            BeanUtils.copyProperties(userImportVO, entity);
            entity.setPassword(passwordEncoder.encode(SystemConstants.DEFAULT_PASSWORD));   // 默认密码


            // 角色解析
            String roleCodes = userImportVO.getRoleCodes();
            List<Long> roleIds = null;
            if (StrUtil.isNotBlank(roleCodes)) {
                roleIds = roleService.list(
                                new LambdaQueryWrapper<SysRole>()
                                        .in(SysRole::getCode, roleCodes.split(","))
                                        .eq(SysRole::getStatus, StatusEnum.ENABLE.getValue())
                                        .select(SysRole::getId)
                        ).stream()
                        .map(SysRole::getId)
                        .collect(Collectors.toList());
            }


            boolean saveResult = userService.save(entity);
            if (saveResult) {
                validCount++;
                // 保存用户角色关联
                if (CollectionUtil.isNotEmpty(roleIds)) {
                    List<SysUserRole> userRoles = roleIds.stream()
                            .map(roleId -> new SysUserRole(entity.getId(), roleId))
                            .collect(Collectors.toList());
                    userRoleService.saveBatch(userRoles);
                }
            } else {
                invalidCount++;
                msg.append("第").append(validCount + invalidCount).append("行数据保存失败；<br/>");
            }
        } else {
            invalidCount++;
            msg.append("第").append(validCount + invalidCount).append("行数据校验失败：").append(validationMsg).append("<br/>");
        }
    }


    /**
     * 所有数据解析完成会来调用
     *
     * @param analysisContext 解析上下文
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    @Override
    public String getMsg() {
        // 总结信息
        return StrUtil.format("导入用户结束：成功{}条，失败{}条；<br/>{}", validCount, invalidCount, msg);
    }
}

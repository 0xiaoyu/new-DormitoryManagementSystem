package com.yu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.common.constant.SecurityConstants;
import com.yu.common.util.SecurityUtils;
import com.yu.mapper.SysUserMapper;
import com.yu.model.dto.UserAuthInfo;
import com.yu.model.entity.SysUser;
import com.yu.model.query.UserPageQuery;
import com.yu.model.vo.UserInfoVO;
import com.yu.model.vo.UserPageVO;
import com.yu.service.SysMenuService;
import com.yu.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
* @author za'y
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-08-20 12:44:54
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

    @Resource
    private RedisTemplate<String, Set<String>> redisTemplate;
    @Resource
    private SysMenuService menuService;

    @Override
    public UserAuthInfo getUserAuthInfo(String usernameOrEmail) {
        boolean b = usernameOrEmail.indexOf('@') == -1;
        UserAuthInfo userAuthInfo = this.baseMapper.getUserAuthInfo(b?usernameOrEmail:null,b?null:usernameOrEmail);
        if (userAuthInfo != null) {
            Set<String> roles = userAuthInfo.getRoles();
            if (CollectionUtil.isNotEmpty(roles)) {
                Set<String> perms = menuService.listRolePerms(roles);
                userAuthInfo.setPerms(perms);
            }
        }
        return userAuthInfo;
    }

    @Override
    public UserInfoVO getUserLoginInfo() {
        SysUser user = getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getName, Objects.requireNonNull(SecurityUtils.getUser()).getUsername()));
        UserInfoVO info = new UserInfoVO();
        BeanUtil.copyProperties(user, info);
        info.setNickname(user.getName());
        info.setRoles(SecurityUtils.getRoles());
        info.setPerms(redisTemplate.opsForValue().get(SecurityConstants.USER_PERMS_CACHE_PREFIX+ user.getId()));
        return info;
    }

    @Override
    public IPage<UserPageVO> getUserPage(UserPageQuery queryParams) {
        Page<UserPageVO> page = new Page<>(queryParams.getPageNum(), queryParams.getPageSize());
        return this.baseMapper.getUserPage(page, queryParams);
    }

    @Override
    public List<Long> getAllStudentSysUserId() {
        return baseMapper.getAllStudentSysUserId();
    }

    @Override
    public List<Long> getAllSysUserIdByStudentIds(String[] ids) {
        return baseMapper.getAllSysUserIdByStudentIds(ids);
    }


}





package com.yu.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.common.model.Option;
import com.yu.model.entity.SysRole;
import com.yu.model.form.RoleForm;
import com.yu.model.query.RolePageQuery;

import java.util.List;
import java.util.Set;

/**
 * 角色业务接口层
 *
 * @author zay
 * @since 2023/8/25
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 角色分页列表
     *
     * @param queryParams
     * @return
     */
    Page<SysRole> getRolePage(RolePageQuery queryParams);


    /**
     * 角色下拉列表
     *
     * @return
     */
    List<Option<Long>> listRoleOptions();

    /**
     *
     * @param roleForm
     * @return
     */
    boolean saveRole(SysRole roleForm);

    /**
     * 获取角色表单数据
     *
     * @param roleId 角色ID
     * @return {@link RoleForm} – 角色表单数据
     */
    SysRole getRoleForm(Long roleId);


    /**
     * 批量删除角色
     *
     * @param ids 角色ID，多个使用英文逗号(,)分割
     * @return
     */
    boolean deleteRoles(String ids);


    /**
     * 获取角色的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合(包括按钮权限ID)
     */
    List<Long> getRoleMenuIds(Long roleId);


    /**
     * 修改角色的资源权限
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean updateRoleMenus(Long roleId, List<Long> menuIds);

    /**
     * 获取最大范围的数据权限
     *
     * @param roles
     * @return
     */
    Integer getMaximumDataScope(Set<String> roles);


}

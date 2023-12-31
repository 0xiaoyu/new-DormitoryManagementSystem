package com.yu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.common.model.Option;
import com.yu.model.entity.SysDictType;
import com.yu.model.query.DictTypePageQuery;

import java.util.List;

/**
 * 数据字典类型业务接口
 *
 * @author zay
 * @since 2023/8/25
 */
public interface SysDictTypeService extends IService<SysDictType> {

    /**
     * 字典类型分页列表
     *
     * @param queryParams 分页查询对象
     * @return
     */
    Page<SysDictType> getDictTypePage(DictTypePageQuery queryParams);


    /**
     * 获取字典类型表单详情
     *
     * @param id 字典类型ID
     * @return
     */
    SysDictType getDictTypeForm(Long id);


    /**
     * 新增字典类型
     *
     * @param dictTypeForm 字典类型表单
     * @return
     */
    boolean saveDictType(SysDictType dictTypeForm);


    /**
     * 修改字典类型
     *
     * @param id
     * @param dictTypeForm 字典类型表单
     * @return
     */
    boolean updateDictType(Long id, SysDictType dictTypeForm);

    /**
     * 删除字典类型
     *
     * @param idsStr 字典类型ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteDictTypes(String idsStr);


    /**
     * 获取字典类型的数据项
     *
     * @param typeCode
     * @return
     */
    List<Option<String>> listDictItemsByTypeCode(String typeCode);
}

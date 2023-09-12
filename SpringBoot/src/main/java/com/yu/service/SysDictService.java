package com.yu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yu.common.model.Option;
import com.yu.model.entity.SysDict;
import com.yu.model.query.DictPageQuery;
import com.yu.model.vo.DictPageVO;

import java.util.List;

/**
 * 字典接口
 *
 * @author zay
 * @since 2023/3/4
 */
public interface SysDictService extends IService<SysDict> {
    /**
     * 字典数据项分页列表
     *
     * @param queryParams 分页查询对象
     * @return 分页对象
     */
    Page<DictPageVO> getDictPage(DictPageQuery queryParams);

    /**
     * 字典数据项表单
     *
     * @param id 字典数据项ID
     * @return 字典数据项表单
     */
    SysDict getDictForm(Long id);

    /**
     * 新增字典数据项
     *
     * @param dictForm 字典数据项表单
     * @return 是否成功
     */
    boolean saveDict(SysDict dictForm);

    /**
     * 修改字典数据项
     *
     * @param id       字典数据项ID
     * @param dictForm 字典数据项表单
     * @return 是否成功
     */
    boolean updateDict(Long id, SysDict dictForm);

    /**
     * 删除字典数据项
     *
     * @param idsStr 字典数据项ID，多个以英文逗号(,)分割
     * @return  是否成功
     */
    boolean deleteDict(String idsStr);

    /**
     * 获取字典下拉列表
     *
     * @param typeCode 字典类型编码
     * @return 字典下拉列表
     */
    List<Option<String>> listDictOptions(String typeCode);

}

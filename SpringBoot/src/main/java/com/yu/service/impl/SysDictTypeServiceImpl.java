package com.yu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.common.model.Option;
import com.yu.mapper.SysDictTypeMapper;
import com.yu.model.entity.SysDict;
import com.yu.model.entity.SysDictType;
import com.yu.model.query.DictTypePageQuery;
import com.yu.service.SysDictService;
import com.yu.service.SysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典类型业务实现类
 *
 * @author haoxr
 * @since 2022/10/12
 */
@Service
@RequiredArgsConstructor
public class SysDictTypeServiceImpl extends ServiceImpl<SysDictTypeMapper, SysDictType> implements SysDictTypeService {


    private final SysDictService dictItemService;

    /**
     * 字典分页列表
     *
     * @param queryParams 分页查询对象
     * @return 分页结果
     */
    @Override
    public Page<SysDictType> getDictTypePage(DictTypePageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();
        Integer status = queryParams.getStatus();

        // 查询数据
        return this.page(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<SysDictType>()
                        .like(StrUtil.isNotBlank(keywords), SysDictType::getName, keywords)
                        .or()
                        .like(StrUtil.isNotBlank(keywords), SysDictType::getCode, keywords)
                        .eq(status!=null, SysDictType::getStatus, status)
                        .select(
                                SysDictType::getId,
                                SysDictType::getName,
                                SysDictType::getCode,
                                SysDictType::getStatus,
                                SysDictType::getRemark
                        )
        );
    }

    /**
     * 获取字典类型表单详情
     *
     * @param id 字典类型ID
     * @return 字典类型表单
     */
    @Override
    public SysDictType getDictTypeForm(Long id) {
        // 获取entity
        SysDictType entity = this.getOne(new LambdaQueryWrapper<SysDictType>()
                .eq(SysDictType::getId, id)
                .select(
                        SysDictType::getId,
                        SysDictType::getName,
                        SysDictType::getCode,
                        SysDictType::getStatus,
                        SysDictType::getRemark
                ));
        Assert.isTrue(entity != null, "字典类型不存在");
        return entity;
    }

    /**
     * 新增字典类型
     *
     * @param entity 字典类型表单
     * @return 是否成功
     */
    @Override
    public boolean saveDictType(SysDictType entity) {
        return this.save(entity);
    }


    /**
     * 修改字典类型
     *
     * @param id           字典类型ID
     * @param entity 字典类型表单
     * @return 是否成功
     */
    @Override
    public boolean updateDictType(Long id, SysDictType entity) {
        // 获取字典类型
        SysDictType sysDictType = this.getById(id);
        Assert.isTrue(sysDictType != null, "字典类型不存在");
        boolean result = this.updateById(entity);
        if (result) {
            // 字典类型code变化，同步修改字典项的类型code
            String oldCode = sysDictType.getCode();
            String newCode = entity.getCode();
            if (!StrUtil.equals(oldCode, newCode)) {
                dictItemService.update(new LambdaUpdateWrapper<SysDict>()
                        .eq(SysDict::getTypeCode, oldCode)
                        .set(SysDict::getTypeCode, newCode)
                );
            }
        }
        return result;
    }

    /**
     * 删除字典类型
     *
     * @param idsStr 字典类型ID，多个以英文逗号(,)分割
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean deleteDictTypes(String idsStr) {

        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");

        List<String> ids = new ArrayList<>(Arrays.asList(idsStr.split(",")));

        // 删除字典数据项
        List<String> dictTypeCodes = this.list(new LambdaQueryWrapper<SysDictType>()
                        .in(SysDictType::getId, ids)
                        .select(SysDictType::getCode))
                .stream()
                .map(SysDictType::getCode)
                .collect(Collectors.toList()
                );
        if (CollectionUtil.isNotEmpty(dictTypeCodes)) {
            dictItemService.remove(new LambdaQueryWrapper<SysDict>()
                    .in(SysDict::getTypeCode, dictTypeCodes));
        }
        // 删除字典类型
        return this.removeByIds(ids);
    }

    /**
     * 获取字典类型的数据项
     *
     * @param typeCode 字典类型code
     * @return 字典数据项
     */
    @Override
    public List<Option<String>> listDictItemsByTypeCode(String typeCode) {
        // 数据字典项
        List<SysDict> dictItems = dictItemService.list(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getTypeCode, typeCode)
                .select(SysDict::getValue, SysDict::getName)
        );

        // 转换下拉数据
        return CollectionUtil.emptyIfNull(dictItems)
                .stream()
                .map(dictItem -> new Option<>(dictItem.getValue(), dictItem.getName()))
                .collect(Collectors.toList());
    }


}





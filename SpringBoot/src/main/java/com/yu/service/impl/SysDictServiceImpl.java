package com.yu.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yu.common.model.Option;
import com.yu.mapper.SysDictMapper;
import com.yu.model.entity.SysDict;
import com.yu.model.query.DictPageQuery;
import com.yu.model.vo.DictPageVO;
import com.yu.service.SysDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据字典项业务实现类
 *
 * @author zay
 * @since 2023/8/25
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {


    @Override
    public Page<DictPageVO> getDictPage(DictPageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();
        String typeCode = queryParams.getTypeCode();

        // 查询数据
        Page<SysDict> dictItemPage = this.page(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<SysDict>()
                        .like(StrUtil.isNotBlank(keywords), SysDict::getName, keywords)
                        .eq(StrUtil.isNotBlank(typeCode), SysDict::getTypeCode, typeCode)
                        .select(SysDict::getId, SysDict::getName, SysDict::getValue, SysDict::getStatus)
        );

        // 实体转换
        Page<DictPageVO> pageResult = new Page<>();
        BeanUtils.copyProperties(dictItemPage, pageResult);
        return pageResult;
    }

    @Override
    public SysDict getDictForm(Long id) {
        // 获取entity
        SysDict entity = this.getOne(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getId, id)
                .select(
                        SysDict::getId,
                        SysDict::getTypeCode,
                        SysDict::getName,
                        SysDict::getValue,
                        SysDict::getStatus,
                        SysDict::getSort,
                        SysDict::getRemark
                ));
        Assert.isTrue(entity != null, "字典数据项不存在");
        return entity;
    }

    @Override
    public boolean saveDict(SysDict entity) {
        // 持久化
        return this.save(entity);
    }

    @Override
    public boolean updateDict(Long id, SysDict entity) {
        return this.updateById(entity);
    }

    @Override
    @Transactional
    public boolean deleteDict(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");
        //
        List<Long> ids = Arrays.stream(idsStr.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        // 删除字典数据项
        return this.removeByIds(ids);
    }

    @Override
    public List<Option<String>> listDictOptions(String typeCode) {
        // 数据字典项
        List<SysDict> dictList = this.list(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getTypeCode, typeCode)
                .select(SysDict::getValue, SysDict::getName)
        );

        // 转换下拉数据
        return CollectionUtil.emptyIfNull(dictList)
                .stream()
                .map(dictItem -> new Option<String>(dictItem.getValue(), dictItem.getName()))
                .collect(Collectors.toList());
    }
}





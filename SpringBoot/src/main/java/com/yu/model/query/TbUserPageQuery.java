package com.yu.model.query;

import com.yu.common.base.BasePageQuery;
import com.yu.common.enums.UsetTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TbUserPageQuery extends BasePageQuery {
    private String name;
    private UsetTypeEnum typeId;
}

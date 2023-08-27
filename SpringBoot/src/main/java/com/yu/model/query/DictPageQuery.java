package com.yu.model.query;


import com.yu.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description ="字典数据项分页查询对象")
@Data
public class DictPageQuery extends BasePageQuery {

    @Schema(description="关键字(字典项名称)")
    private String keywords;

    @Schema(description="字典类型编码")
    private String typeCode;

    @Schema(description="字典项状态(1-正常；0-停用)")
    private Integer status;
}

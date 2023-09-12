package com.yu.model.query;

import com.yu.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色分页查询实体
 *
 * @author zay
 * @since 2023/8/25
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RolePageQuery extends BasePageQuery {

    @Schema(description="关键字(角色名称/角色编码)")
    private String keywords;
}

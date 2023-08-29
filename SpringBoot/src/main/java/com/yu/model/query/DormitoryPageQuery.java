package com.yu.model.query;

import com.yu.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "宿舍分页查询对象")
public class DormitoryPageQuery extends BasePageQuery {
    @Schema(description = "楼栋id",example = "1")
    private Long buildingId;

    @Schema(description = "楼层",example = "1")
    private Integer floor;

    @Schema(description = "宿舍号",example = "")
    private Integer dormitoryNumber;

    @Schema(description = "水情况",example = "1")
    private Integer wStatus;

    @Schema(description = "电情况",example = "1")
    private Integer eStatus;
}

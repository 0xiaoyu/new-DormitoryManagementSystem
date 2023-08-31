package com.yu.common.model;

import com.yu.model.entity.Building;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description ="地图标点信息")
@Data
@NoArgsConstructor
public class BmMarker {

    @Schema(description = "坐标点的位置" ,required = true)
    private Position position;

    @Schema(description = "鼠标移到marker上的显示内容")
    private String title;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "是否可拖拽")
    private Boolean dragging;

    public BmMarker(Building building){
        this.position = new Position(building.getLongitude(),building.getLatitude());
        this.title = building.getBuildName();
    }

    record Position(double lng,double lat){

    }
}

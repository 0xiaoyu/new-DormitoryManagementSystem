package com.yu.model.vo;

import com.yu.model.entity.UserEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DormitoryPageVo extends UserEntity {
    private String buildingName;
}

package com.yu.model.query;

import com.yu.common.base.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PassLogPageQuery extends BasePageQuery {
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public String type;
}

package com.yu.model.vo;

import com.yu.common.enums.NoticeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "通知消息")
public class NoticeVo {

    @Schema(description = "通知名字")
    private String name;

    @Schema(description = "通知内容")
    private String msg;

    @Schema(description = "是否已读")
    private Boolean isRead;

    @Schema(description = "通知类型")
    private NoticeEnum type;
}

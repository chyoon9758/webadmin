package com.web.admin.DTO.api.getStatus.idolLive.inputStatistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputStatisticsAPI {
    private String id;
    private String videoInfo;
    private String audioRateLevel;
    private String audioMaxlevel;
    private String aspectRatio;
    private String frames;
    private String uptime;
    private ThumbnailInfoAPI thumbnailInfo;
}

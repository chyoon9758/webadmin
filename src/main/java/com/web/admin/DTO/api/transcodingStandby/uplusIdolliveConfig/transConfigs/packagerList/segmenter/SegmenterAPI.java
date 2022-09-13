package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.segmenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SegmenterAPI {
    private DrmAPI drm;
    private String service;
    private String container;
    private String mpd_path;
    private String mpd_name;
    private String segment_path_prefix;
    private String m3u8_path;
    private String m3u8_name;
    private String segment_duration;
    private String window;
    private String minupdate_period;
    private String preserved_segments;
}

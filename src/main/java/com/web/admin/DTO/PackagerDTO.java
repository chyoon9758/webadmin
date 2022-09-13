package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackagerDTO {
    private String id;
    private String group_id;
    private String enable;
    private String kms_url;
    private String kms_token;
    private String content_id;
    private String service;
    private String container;
    private String mpd_path;
    private String mpd_name;
    private String segment_path_prefix;
    private String m3u8_path;
    private String m3u8_name;
    private int segment_duration;
    private int window;
    private int minupdate_period;
    private int preserved_segments;
    private String dash;
    private String hls;
    private String accessKey;
    private String name;
    private String endpoint;
    private String secretKey;
    private String region;
    private String date;
    private String create_time;
    private int selected;
}

package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PresetVideoDTO {
    private String id;
    private String group_id;
    private String codec;
    private int width;
    private int height;
    private int bitrate;
    private String profile;
    private String level;
    private String fps;
    private int iframe_interval;
    private String create_time;

    private String id_concat;
    private int selected;


    private List<PresetVideoDTO> videolist;

}

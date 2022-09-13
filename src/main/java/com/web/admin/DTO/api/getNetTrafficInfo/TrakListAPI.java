package com.web.admin.DTO.api.getNetTrafficInfo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrakListAPI {
    private String type;
    private int id;
    private String codec;
    private int samplerate;
    private float channel;
    private int bitrate;
    private String language;
    private String profile;
    private float level;
    private int width;
    private int height;
    private float fps;
    private int bit_depth;
    private String color;
}

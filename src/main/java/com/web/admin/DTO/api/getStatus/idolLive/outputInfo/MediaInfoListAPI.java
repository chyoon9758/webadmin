package com.web.admin.DTO.api.getStatus.idolLive.outputInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaInfoListAPI {
    private String type;
    private String presetCodec;
    private String presetWidth;
    private String presetHeight;
    private String presetFps;
    private String presetRate;
    private String presetSampleRate;
    private String presetChannel;
    private String rate;
    private String fps;
    private String name;
}

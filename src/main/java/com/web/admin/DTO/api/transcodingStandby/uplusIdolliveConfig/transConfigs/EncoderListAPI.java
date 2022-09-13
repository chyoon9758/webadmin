package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EncoderListAPI {
    private String id;
    private String width;
    private String height;
    private String bitrate;
    private String codec;

    //연동규격서 참고
    private String profile;
    private String level;
    private String fps;
    private String iframe_interval;
}

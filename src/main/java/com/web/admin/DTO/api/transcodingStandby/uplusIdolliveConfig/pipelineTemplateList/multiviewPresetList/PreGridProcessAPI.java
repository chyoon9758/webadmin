package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList.multiviewPresetList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties({"active_video_id", "audio_activation"})
public class PreGridProcessAPI {
    private String source_id;
    //private String active_video_id;
    //private String audio_activation;
    private String resizer_id;
}

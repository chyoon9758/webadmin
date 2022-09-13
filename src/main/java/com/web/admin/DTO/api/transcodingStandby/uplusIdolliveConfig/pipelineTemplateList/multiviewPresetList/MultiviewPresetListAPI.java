package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList.multiviewPresetList;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiviewPresetListAPI {
    private String id;
    @JsonProperty("pre-grid-process")
    private List<PreGridProcessAPI> pre_grid_process;
    private GridComposerAPI grid_composer;
    private List<AbrResizerAPI> abr_resizer;
    private List<EncoderAPI> encoder;
}

package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList;

import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList.multiviewPresetList.MultiviewPresetListAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PipelineTemplateListAPI {
    private List<MultiviewPresetListAPI> multiview_preset_list;
    private PackagerAPI packager;
}

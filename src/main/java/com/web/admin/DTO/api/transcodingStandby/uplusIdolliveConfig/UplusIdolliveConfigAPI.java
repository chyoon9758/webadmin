package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig;

import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList.PipelineTemplateListAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.TransConfigsAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UplusIdolliveConfigAPI {
    private TransConfigsAPI trans_configs;
    private List<PipelineTemplateListAPI> pipeline_template_list;
}

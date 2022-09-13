package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs;

import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.gridComposerList.GridComposerListAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.PackagerListAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransConfigsAPI {
    private List<InputListAPI> input_list;
    private List<ResizerListAPI> resizer_list;
    private List<GridComposerListAPI> grid_composer_list;
    private List<AbrResizerListAPI> abr_resizer_list;
    private List<EncoderListAPI> encoder_list;
    private List<PackagerListAPI> packager_list;
}

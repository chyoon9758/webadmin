package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.gridComposerList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GridComposerListAPI {
    private String id;
    private String width;
    private String height;
    private List<GridMatrixAPI> grid_matrix;
}

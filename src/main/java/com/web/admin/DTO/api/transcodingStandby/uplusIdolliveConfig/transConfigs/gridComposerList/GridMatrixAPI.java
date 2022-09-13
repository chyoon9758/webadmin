package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.gridComposerList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("major")
public class GridMatrixAPI {
//    private boolean major;
    private String left;
    private String top;
    private String right;
    private String bottom;
}

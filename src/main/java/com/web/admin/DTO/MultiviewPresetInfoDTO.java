package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultiviewPresetInfoDTO {
    private String group_id;
    private String preset_group_id;
    private String id;
    private String pre_grid_process_1;
    private String pre_grid_process_2;
    private String pre_grid_process_3;
    private String pre_grid_process_4;
    private String pre_grid_process_5;
    private String pre_grid_process_6;
    private String pre_grid_process_7;
    private String pre_grid_process_8;
    private String pre_grid_process_9;
    private String pre_grid_process_10;
    private String pre_grid_process_11;
    private String grid_composer;
    private String abr_resizer;
    private String encoder;
}

package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolesDTO {
    private String id;
    private String preset_group_id_1;
    private String packager_id_1;
    private String preset_group_id_2;
    private String packager_id_2;
    private String input_group_id;
    private String resize_group_id;
    private String grid_composer_group_id;
    private String abr_resizer_group_id;
    private String encoder_group_id;
    private String packager_group_id;
    private String create_time;
    private int input_streams;
}

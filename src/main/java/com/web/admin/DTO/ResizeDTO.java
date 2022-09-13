package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResizeDTO {
    private String group_id;
    private String id;
    private int width;
    private int height;
    private String create_time;
    private int selected;
}

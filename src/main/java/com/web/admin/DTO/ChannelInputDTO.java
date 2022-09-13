package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelInputDTO {
    private String id;
    private String group_id;
    private String address;
    private String input_interface;
    private String broadcasted;
    private String create_time;
    private int selected;
}

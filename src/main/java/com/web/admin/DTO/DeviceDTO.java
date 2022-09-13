package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceDTO {
    private String name;
    private String origin_name;
    private String ip;
    private String role;
    private String create_time;
}

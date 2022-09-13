package com.web.admin.DTO.api.resync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResyncInfoAPI {
    private String id;
    private String resyncTime;
}

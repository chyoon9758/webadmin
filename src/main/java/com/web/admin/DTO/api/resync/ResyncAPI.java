package com.web.admin.DTO.api.resync;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResyncAPI {
    private List<ResyncInfoAPI> resyncInfo;
}

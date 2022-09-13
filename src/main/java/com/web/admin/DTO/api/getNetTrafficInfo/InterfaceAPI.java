package com.web.admin.DTO.api.getNetTrafficInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterfaceAPI {
    private String name;
    private List<SourceListAPI> source_list;
}

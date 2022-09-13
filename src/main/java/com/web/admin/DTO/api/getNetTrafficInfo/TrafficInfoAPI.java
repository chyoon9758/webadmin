package com.web.admin.DTO.api.getNetTrafficInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrafficInfoAPI {
    @JsonProperty("interface")
    private List<InterfaceAPI> interface_list;
}

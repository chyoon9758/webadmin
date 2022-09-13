package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputListAPI {
    private String id;
    private String address;
    @JsonProperty("interface")
    private String input_interface;
    private String broadcasted;
}

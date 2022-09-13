package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishingInfoAPI {
    private String name;
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String region;
}

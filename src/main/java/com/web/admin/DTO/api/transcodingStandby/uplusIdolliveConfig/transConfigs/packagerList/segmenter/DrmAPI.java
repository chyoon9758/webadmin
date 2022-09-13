package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.segmenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrmAPI {
    private String enable;
    private String kms_url;
    private String kms_token;
    private String content_id;
}

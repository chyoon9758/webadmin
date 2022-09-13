package com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList;

import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.segmenter.SegmenterAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackagerListAPI {
    private String id;
    private SegmenterAPI segmenter;
    private ManifestGeneratorAPI manifest_generator;
    private PublishingInfoAPI publishing_info;
}

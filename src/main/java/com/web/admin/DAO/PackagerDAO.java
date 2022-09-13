package com.web.admin.DAO;

import com.web.admin.DTO.PackagerDTO;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.ManifestGeneratorAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.PublishingInfoAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.segmenter.DrmAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.segmenter.SegmenterAPI;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PackagerDAO {
    List<PackagerDTO> getListAll();
    PackagerDTO getPackagerByGroupId(String group_id);
    void insertRow(PackagerDTO packagerDTO);
    void deleteByGroupId(String group_id);
    void updateRow(PackagerDTO packagerDTO);
    DrmAPI getDrm(PackagerDTO packagerDTO);
    SegmenterAPI getSegmenter(PackagerDTO packagerDTO);
    ManifestGeneratorAPI getManifestGenerator(PackagerDTO packagerDTO);
    PublishingInfoAPI getPublishingInfo(PackagerDTO packagerDTO);
}

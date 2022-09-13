package com.web.admin.DAO.transcodingStandby;

import com.web.admin.DTO.MultiviewPresetInfoDTO;
import com.web.admin.DTO.TransRoleInfoDTO;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList.multiviewPresetList.EncoderAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList.multiviewPresetList.PreGridProcessSplitAPI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TranscodingStandbyDAO {
    List<EncoderAPI> getAllEncoder();
    TransRoleInfoDTO getTransRoleInfo(String id);
    List<MultiviewPresetInfoDTO> getMultiviewPresetInfo(@Param("group_id") String group_id,@Param("preset_group_id") String preset_group_id);
    List<PreGridProcessSplitAPI> getPreGridProcessSplitAPI(@Param("group_id") String group_id,@Param("group_id") String preset_group_id);
}

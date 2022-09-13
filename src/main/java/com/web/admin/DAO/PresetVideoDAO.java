package com.web.admin.DAO;

import com.web.admin.DTO.PresetVideoDTO;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.EncoderListAPI;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PresetVideoDAO {

    List<PresetVideoDTO> getListGroupConcat(); //id 기준으로 rows 정렬을 위한 전체 조회
    List<PresetVideoDTO> getListAll();
    List<PresetVideoDTO> getInputByPresetId(String group_id); //하나의 id 의 rows 조회
    PresetVideoDTO getInputByData(String group_id); //하나의 id로 내 create_time 조회

    List<EncoderListAPI> getEncoderListAPI(String group_id);

    void insertVideoData(List<PresetVideoDTO> presetVideoDTO);

    void deletePresetId(String group_id);
}

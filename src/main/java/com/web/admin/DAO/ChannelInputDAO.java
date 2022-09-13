package com.web.admin.DAO;

import com.web.admin.DTO.ChannelInputConcatDTO;
import com.web.admin.DTO.ChannelInputDTO;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.InputListAPI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ChannelInputDAO {
    List<ChannelInputConcatDTO> getListGroupConcat(); //group_id 기준으로 rows 정렬을 위한 전체 조회

    List<ChannelInputDTO> getListAll();

    List<ChannelInputDTO> getInputByGroupId(String group_id); //하나의 group_id 내 rows 조회
    List<InputListAPI> getInputListAPI(String group_id);

    void insertList(@Param("channelInputDTOList") List<ChannelInputDTO> channelInputDTOList,@Param("now") Boolean now);

    void deleteListByGroupId(String group_id);
}


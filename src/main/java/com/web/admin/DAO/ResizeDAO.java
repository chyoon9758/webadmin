package com.web.admin.DAO;

import com.web.admin.DTO.ResizeConcatDTO;
import com.web.admin.DTO.ResizeDTO;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.AbrResizerListAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.ResizerListAPI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ResizeDAO {
    List<ResizeConcatDTO> getListGroupConcat(); //group_id 기준으로 rows 정렬을 위한 전체 조회
    List<ResizeDTO> getListAll();
    List<ResizeDTO> getResizeByGroupId(String group_id); //하나의 group_id 내 rows 조회
    List<ResizerListAPI> getResizerAPI(String group_id);
    List<AbrResizerListAPI> getAbrResizerAPI(String group_id);
    void deleteListByGroupId(String group_id);
    void insertList(@Param("resizeDTOList") List<ResizeDTO> resizeDTOList,@Param("now") Boolean now);
}

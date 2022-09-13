package com.web.admin.DAO;

import com.web.admin.DTO.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface RolesDAO {

    List<RolesDTO> getListRoles();
    RolesDTO getSelectRoles(String id);
    List<ChannelInputDTO> getListInput();
    List<ChannelInputDTO> getListSourceId(String group_id);
    List<ComposerDTO> getListComposer();
    List<ComposerDTO> getListComposerId(String group_id);
    List<PackagerDTO> getListPackager();
    List<PresetVideoDTO> getListVideo();
    List<PresetVideoDTO> getListVideoId(String group_id);
    List<ResizeDTO> getListResizer();
    List<ResizeDTO> getListResizerId(String group_id);
    List<ResizeDTO> getListAbrResizer();
    List<ResizeDTO> getListAbrResizerId(String group_id);
    String getStreamsInput(String group_id); // input의 group_id count 가져오기
    List<HashMap<String, String>> getPresetGroupId(@Param("group_id") String group_id,@Param("id") String id); // preset_group_id_1/2 데이터 가져오기
    void insertRoles(RolesDTO rolesDTO);
    void insertPresetInfo(List<HashMap<String, String>> listPresetInfoDTO);

    void updateSelectedByInput(); // 사용 중 설정 값 확인 후 selected 옵션 변경
    void updateSelectedByResize();
    void updateSelectedByComposer();
    void updateSelectedByVideo();
    void updateSelectedByPackager();
    String getInput(String id);
    void deleteRolesId(String id);
    void deletePresetId(String group_id);
}

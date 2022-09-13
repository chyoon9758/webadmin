package com.web.admin.DAO;

import com.web.admin.DTO.ComposerConcatDTO;
import com.web.admin.DTO.ComposerDTO;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.gridComposerList.GridComposerListAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.gridComposerList.GridComposerListSplitAPI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ComposerDAO {
    List<ComposerDTO> getListAll();
    List<ComposerConcatDTO> getListGroupConcat();
    List<ComposerDTO> getComposerByGroupId(String group_id);
    void insertList(@Param("composerDTOList")List<ComposerDTO> composerDTOList , @Param("now") Boolean now);
    void deleteListByGroupId(String group_id);
    List<GridComposerListAPI> getGridComposerListAPI(String group_id);
    List<GridComposerListSplitAPI> getGridComposerListSplitAPI(String group_id);
}

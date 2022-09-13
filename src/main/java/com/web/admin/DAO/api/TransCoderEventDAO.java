package com.web.admin.DAO.api;

import com.web.admin.DTO.api.notifyingEvent.IdolLiveEventAPI;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TransCoderEventDAO {

    List<IdolLiveEventAPI> getListAll(int trnascoderlogcnt);
    void insertTransCoderEvent(IdolLiveEventAPI idolLiveEventAPI);
    void deleteTransCoderLog(int trnascoderlogcnt);
    IdolLiveEventAPI getStatus();
}

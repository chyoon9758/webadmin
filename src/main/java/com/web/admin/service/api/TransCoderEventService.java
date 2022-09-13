package com.web.admin.service.api;

import com.web.admin.DAO.api.TransCoderEventDAO;
import com.web.admin.DTO.api.notifyingEvent.IdolLiveEventAPI;

import com.web.admin.DTO.api.notifyingEvent.NotifyingEventAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TransCoderEventService {

    @Value("${transcoder.logcount}")
    private int transcoderlogcnt;
    private final TransCoderEventDAO transCoderEventDAO;

    public List<IdolLiveEventAPI> getListAll(int transcoderlogcnt){
        return transCoderEventDAO.getListAll(transcoderlogcnt);
    }

    public void insertTransCoderEvent(NotifyingEventAPI data){

        IdolLiveEventAPI idolLiveEventAPI = new IdolLiveEventAPI();
        idolLiveEventAPI.setStatus(data.getIdolLiveEvent().get(0).getStatus());
        idolLiveEventAPI.setMessageLevel(data.getIdolLiveEvent().get(0).getMessageLevel());
        idolLiveEventAPI.setMessage(data.getIdolLiveEvent().get(0).getMessage());
        idolLiveEventAPI.setErrorCode(data.getIdolLiveEvent().get(0).getErrorCode());
        idolLiveEventAPI.setErrorString(data.getIdolLiveEvent().get(0).getErrorString());
        idolLiveEventAPI.setErrorCode2(data.getIdolLiveEvent().get(0).getErrorCode2());
        idolLiveEventAPI.setErrorString2(data.getIdolLiveEvent().get(0).getErrorString2());
        idolLiveEventAPI.setElapsedTime(data.getIdolLiveEvent().get(0).getElapsedTime());

        transCoderEventDAO.insertTransCoderEvent(idolLiveEventAPI);
    }
    public void deleteTransCoderLog(int transcoderlogcnt){
        transCoderEventDAO.deleteTransCoderLog(transcoderlogcnt);
    }

    public IdolLiveEventAPI getStatus(){
        return transCoderEventDAO.getStatus();
    }
}

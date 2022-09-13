package com.web.admin.service.api;

import com.web.admin.DTO.api.resync.ResyncAPI;
import com.web.admin.DTO.api.resync.ResyncInfoAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ResyncTimestampService {

    public ResyncAPI getResyncTimestamp(ResyncInfoAPI resyncInfoAPI){

        ResyncAPI resyncAPI = new ResyncAPI();

        String[] id = resyncInfoAPI.getId().split(",");
        String[] resyncTime = resyncInfoAPI.getResyncTime().split(",",-1);

        List<ResyncInfoAPI> list = new ArrayList<ResyncInfoAPI>();
        for(int i=0; i<id.length; i++){
            if(!resyncTime[i].equals("0") && !resyncTime[i].equals("0.") && !resyncTime[i].equals("") && !resyncTime[i].matches("[0,.]+")){
                ResyncInfoAPI resyncInfoAPI1 = new ResyncInfoAPI();
                resyncInfoAPI1.setId(id[i]);
                if(resyncTime[i].indexOf(".")==0){
                    resyncInfoAPI1.setResyncTime("0"+resyncTime[i]);
                }else if(resyncTime[i].indexOf(".")==resyncTime[i].length()-1){
                    resyncInfoAPI1.setResyncTime(resyncTime[i].replace(".",""));
                }else{
                    resyncInfoAPI1.setResyncTime(resyncTime[i]);
                }

                list.add(resyncInfoAPI1);
            }
        }
        resyncAPI.setResyncInfo(list);

        return resyncAPI;
    }
}

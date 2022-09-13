package com.web.admin.service;


import com.web.admin.DAO.PresetVideoDAO;
import com.web.admin.DTO.PresetVideoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PresetVideoService {

    private final PresetVideoDAO presetVideoDAO;

    public List<PresetVideoDTO> getListAll() { return presetVideoDAO.getListAll();}

    public List<PresetVideoDTO> getListGroupConcat(){
        return presetVideoDAO.getListGroupConcat();
    }
    public List<PresetVideoDTO> getInputByPresetId(String group_id){
        return presetVideoDAO.getInputByPresetId(group_id);
    }
    public PresetVideoDTO getInputByData(String group_id){
        return presetVideoDAO.getInputByData(group_id);
    }

    public void insertVideoData(HttpServletRequest request,PresetVideoDTO presetVideoDTO){

        List<PresetVideoDTO> list = new ArrayList<PresetVideoDTO>();
        int cnt = request.getParameterValues("id").length;
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(presetVideoDTO.getGroup_id() != null) {
            for (int i = 0; i < cnt; i++) {
                PresetVideoDTO presetVideoDTO1 = new PresetVideoDTO();
                presetVideoDTO1.setGroup_id(presetVideoDTO.getGroup_id());
                presetVideoDTO1.setId(request.getParameterValues("id")[i]);
                presetVideoDTO1.setCodec(request.getParameterValues("codec")[i]);
                presetVideoDTO1.setWidth(Integer.parseInt(request.getParameterValues("width")[i]));
                presetVideoDTO1.setHeight(Integer.parseInt(request.getParameterValues("height")[i]));
                presetVideoDTO1.setBitrate(Integer.parseInt(request.getParameterValues("bitrate")[i]));
                presetVideoDTO1.setProfile(request.getParameterValues("profile")[i]);
                presetVideoDTO1.setLevel(request.getParameterValues("level")[i]);
                presetVideoDTO1.setFps(request.getParameterValues("fps")[i]);
                presetVideoDTO1.setIframe_interval(Integer.parseInt(request.getParameterValues("iframe_interval")[i]));
                presetVideoDTO1.setCreate_time(simpleDateFormat.format(nowDate)); // 현재시간 저장

                list.add(presetVideoDTO1);
            }
        }
        presetVideoDAO.insertVideoData(list);
    }

    public void deletePresetId(String group_id){
        presetVideoDAO.deletePresetId(group_id);
    };

}

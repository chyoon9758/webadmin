package com.web.admin.service;

import com.web.admin.DAO.ResizeDAO;
import com.web.admin.DTO.ResizeConcatDTO;
import com.web.admin.DTO.ResizeDTO;
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
public class ResizeService {
    private final ResizeDAO resizeDAO;

    public List<ResizeConcatDTO> getListGroupConcat(){return resizeDAO.getListGroupConcat();}

    public List<ResizeDTO> getListAll(){return resizeDAO.getListAll();}

    public List<ResizeDTO> getResizeByGroupId(String group_id){return resizeDAO.getResizeByGroupId(group_id);}

    public String updateResize(ResizeDTO resizeDTO, int width[], int height[], String query){
        log.info(resizeDTO + " / " + query);
        if(query.equals("update")){
            deleteListByGroupId(resizeDTO.getGroup_id());
            insertList(resizeDTO, width, height, false);
            return "update";
        }else if(query.equals("insert")){
            if(getResizeByGroupId(resizeDTO.getGroup_id()).size()>0){
                return "duplicate";
            }
            else{
                insertList(resizeDTO, width, height,true);
                return "insert";
            }
        }
        return "fail";
    }
    public void deleteListByGroupId(String group_id){
        resizeDAO.deleteListByGroupId(group_id);
    }
    public void insertList(ResizeDTO resizeDTO, int width[], int height[], Boolean now){
        List<ResizeDTO> resizeDTOList = new ArrayList<>();
        for(int i=0; i<width.length; i++){
            ResizeDTO resizeDTO1 = new ResizeDTO();
            resizeDTO1.setGroup_id(resizeDTO.getGroup_id());
            resizeDTO1.setId(width[i] + "x" + height[i]);
            resizeDTO1.setCreate_time(resizeDTO.getCreate_time());
            resizeDTO1.setSelected(resizeDTO.getSelected());
            resizeDTO1.setWidth(width[i]);
            resizeDTO1.setHeight(height[i]);
            resizeDTOList.add(resizeDTO1);
        }
        log.info(resizeDTOList+"");
        resizeDAO.insertList(resizeDTOList, now);
    }
}

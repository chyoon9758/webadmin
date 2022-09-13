package com.web.admin.service;

import com.web.admin.DAO.ComposerDAO;
import com.web.admin.DTO.ComposerConcatDTO;
import com.web.admin.DTO.ComposerDTO;
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
public class ComposerService {
    private final ComposerDAO composerDAO;

    public List<ComposerDTO> getListAll(){
        return composerDAO.getListAll();
    }
    public List<ComposerConcatDTO> getListGroupConcat(){
        return composerDAO.getListGroupConcat();
    }

    public List<ComposerDTO> getComposerByGroupId(String group_id){
        return composerDAO.getComposerByGroupId(group_id);
    }

    public String trans_grid_matrix(String left, String top, String right, String bottom){
        return left + "/" + top + "/" + right + "/" + bottom;
    }

    public String updateComposer(ComposerDTO composerDTO, int[] width, int[] height, String left[], String top[], String right[], String bottom[], String query){
        if(query.equals("update")){
            deleteListByGroupId(composerDTO.getGroup_id());
            insertList(composerDTO, width, height, left, top, right, bottom, false);
            return "update";
        }else if(query.equals("insert")){
            if(getComposerByGroupId(composerDTO.getGroup_id()).size()>0){
                return "duplicate";
            }else{
                insertList(composerDTO, width, height, left, top, right, bottom, true);
                return "insert";
            }
        }
        return "fail";
    }

    public void insertList(ComposerDTO composerDTO, int[] width, int[] height, String[] left, String[] top, String[] right, String[] bottom, boolean now){ // "," concat to list
        List<ComposerDTO> composerDTOList = new ArrayList<>();

        String splitId[] = composerDTO.getId().split(",", -1); // ","구분자로 연결되어 들어오는 Id를 총 grid 데이터 수에 맞춰서 자르기(공백 포함)
        for(int i=0; i<splitId.length; i++) {
            ComposerDTO composerDTO1 = new ComposerDTO();
            if(!splitId[i].isEmpty()) {
                composerDTO1.setGroup_id(composerDTO.getGroup_id());
                composerDTO1.setCreate_time(composerDTO.getCreate_time());
                composerDTO1.setSelected(composerDTO.getSelected());
                composerDTO1.setId(splitId[i]);
                composerDTO1.setWidth(width[i]);
                composerDTO1.setHeight(height[i]);
                composerDTO1.setGrid_matrix_0(trans_grid_matrix(left[i], top[i], right[i], bottom[i]));
                log.info(composerDTO1+"");

                if(i+1<splitId.length){ // grid_matrix_i
                    if(splitId[i+1].isEmpty()){
                        composerDTO1.setGrid_matrix_1(trans_grid_matrix(left[i+1], top[i+1], right[i+1], bottom[i+1]));
                        if(i+2<splitId.length){
                            if(splitId[i+2].isEmpty()){
                                composerDTO1.setGrid_matrix_2(trans_grid_matrix(left[i+2], top[i+2], right[i+2], bottom[i+2]));
                                if(i+3<splitId.length){
                                    if(splitId[i+3].isEmpty()){
                                        composerDTO1.setGrid_matrix_3(trans_grid_matrix(left[i+3], top[i+3], right[i+3], bottom[i+3]));
                                        if(i+4<splitId.length){
                                            if(splitId[i+4].isEmpty()){
                                                composerDTO1.setGrid_matrix_4(trans_grid_matrix(left[i+4], top[i+4], right[i+4], bottom[i+4]));
                                                if(i+5<splitId.length){
                                                    if(splitId[i+5].isEmpty()){
                                                        composerDTO1.setGrid_matrix_5(trans_grid_matrix(left[i+5], top[i+5], right[i+5], bottom[i+5]));
                                                        if(i+6<splitId.length){
                                                            if(splitId[i+6].isEmpty()){
                                                                composerDTO1.setGrid_matrix_6(trans_grid_matrix(left[i+6], top[i+6], right[i+6], bottom[i+6]));
                                                                if(i+7<splitId.length){
                                                                    if(splitId[i+7].isEmpty()){
                                                                        composerDTO1.setGrid_matrix_7(trans_grid_matrix(left[i+7], top[i+7], right[i+7], bottom[i+7]));
                                                                        if(i+8<splitId.length){
                                                                            if(splitId[i+8].isEmpty()){
                                                                                composerDTO1.setGrid_matrix_8(trans_grid_matrix(left[i+8], top[i+8], right[i+8], bottom[i+8]));
                                                                                if(i+9<splitId.length){
                                                                                    if(splitId[i+9].isEmpty()){
                                                                                        composerDTO1.setGrid_matrix_9(trans_grid_matrix(left[i+9], top[i+9], right[i+9], bottom[i+9]));
                                                                                        if(i+10<splitId.length){
                                                                                            if(splitId[i+10].isEmpty()){
                                                                                                composerDTO1.setGrid_matrix_10(trans_grid_matrix(left[i+10], top[i+10], right[i+10], bottom[i+10]));
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                log.info(composerDTO1+"");
                composerDTOList.add(composerDTO1);
            }
        }
        log.info(composerDTOList +"");
        composerDAO.insertList(composerDTOList, now);
    }
    public void deleteListByGroupId(String group_id){
        composerDAO.deleteListByGroupId(group_id);
    }
}

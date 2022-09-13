package com.web.admin.service;

import com.web.admin.DAO.PackagerDAO;
import com.web.admin.DTO.PackagerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PackagerService {
    private final PackagerDAO packagerDAO;

    public List<PackagerDTO> getListAll(){
        return packagerDAO.getListAll();
    }
    public PackagerDTO getPackagerByGroupId(String group_id){
        return packagerDAO.getPackagerByGroupId(group_id);
    }

    public void deleteByGroupId(String group_id){
        packagerDAO.deleteByGroupId(group_id);
    }

    public String updateRow(PackagerDTO packagerDTO, String query){//신규 입력, 기존 수정 병행
        if(query.equals("insert")) {
            packagerDAO.insertRow(packagerDTO);
            return "insert";
        }
        else if(query.equals("update")) {
            packagerDAO.updateRow(packagerDTO);
            return "update";
        }

        return "fail";
    }
}

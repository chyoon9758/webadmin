package com.web.admin.service;

import com.web.admin.DAO.ChannelInputDAO;
import com.web.admin.DTO.ChannelInputConcatDTO;
import com.web.admin.DTO.ChannelInputDTO;
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
public class ChannelInputService {
    private final ChannelInputDAO channelInputDAO;
    public List<ChannelInputConcatDTO> getListGroupConcat() {
        return channelInputDAO.getListGroupConcat();
    }

    public List<ChannelInputDTO> getListAll() {
        return channelInputDAO.getListAll();
    }

    public List<ChannelInputDTO> getInputByGroupId(String group_id) {
        return channelInputDAO.getInputByGroupId(group_id);
    }
    public String updateChannelInput(ChannelInputDTO channelInputDTO, String query) {
        if(query.equals("update")) {
            deleteListByGroupId(channelInputDTO.getGroup_id());
            insertList(channelInputDTO, false);
            return "update";
        } else if (query.equals("insert")) { //신규 입력의 경우는 기존에 사용중인 group_id를 입력받았는지 체크
            if(getInputByGroupId(channelInputDTO.getGroup_id()).size()>0){
                return "duplicate";
            }else{
                insertList(channelInputDTO, true);
                return "insert";
            }
        }
        return "fail";
    }
    public void deleteListByGroupId(String group_id){
        channelInputDAO.deleteListByGroupId(group_id);
    }
    public void insertList(ChannelInputDTO channelInputDTO, Boolean now){ // ","concat to list, 신규 추가인지 기존 데이터 수정인지 확인
        log.info(channelInputDTO+"");
        List<ChannelInputDTO> channelInputDTOList = new ArrayList<ChannelInputDTO>();
        String splitId[] = channelInputDTO.getId().split(",");
        String splitInput_interface[] = channelInputDTO.getInput_interface().split(",");
        String splitAddress[] = channelInputDTO.getAddress().split(",");
        String splitBroadcasted[] = channelInputDTO.getBroadcasted().split(",");
        for (int i=0; i<splitId.length; i++) {
            ChannelInputDTO channelInputDTO1 = new ChannelInputDTO();
            channelInputDTO1.setGroup_id(channelInputDTO.getGroup_id());
            channelInputDTO1.setCreate_time(channelInputDTO.getCreate_time());
            channelInputDTO1.setSelected(channelInputDTO.getSelected());
            channelInputDTO1.setId(splitId[i]);
            channelInputDTO1.setInput_interface(splitInput_interface[i]);
            channelInputDTO1.setAddress(splitAddress[i]);
            channelInputDTO1.setBroadcasted(splitBroadcasted[i]);
            channelInputDTOList.add(channelInputDTO1);
        }
        log.info(channelInputDTOList+"");
        channelInputDAO.insertList(channelInputDTOList, now);
    }
}

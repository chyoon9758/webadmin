package com.web.admin.service;

import com.web.admin.DAO.ChannelInputDAO;
import com.web.admin.DAO.DeviceDAO;
import com.web.admin.DAO.RolesDAO;
import com.web.admin.DTO.ChannelInputDTO;
import com.web.admin.DTO.DeviceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MonitorService {
    private final DeviceDAO deviceDAO;
    private final ChannelInputDAO channelInputDAO;
    private final RolesDAO rolesDAO;


    public DeviceDTO getDevice(){
        return deviceDAO.getDevice();
    }

    public List<ChannelInputDTO> getInputByGroupId(){
        return channelInputDAO.getInputByGroupId(rolesDAO.getInput(getDevice().getRole()));
    }

}

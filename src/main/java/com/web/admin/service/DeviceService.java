package com.web.admin.service;

import com.web.admin.DAO.DeviceDAO;
import com.web.admin.DTO.DeviceDTO;
import com.web.admin.DTO.RolesDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class DeviceService {

    private final DeviceDAO deviceDAO;

    public List<DeviceDTO> getDeviceList(){
        return deviceDAO.getDeviceList();
    }
    public List<RolesDTO> getRolesList(){
        return deviceDAO.getRolesList();
    }
    public DeviceDTO getSelectDevice(String name){
        return deviceDAO.getSelectDevice(name);
    }

    public void insertDevice(DeviceDTO deviceDTO) {
        deviceDAO.insertDevice(deviceDTO);
    }
    public void updateDevice(DeviceDTO deviceDTO) { deviceDAO.updateDevice(deviceDTO); }
    public void deleteDevice(String name) {
        deviceDAO.deleteDevice(name);
    }
}

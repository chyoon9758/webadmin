package com.web.admin.DAO;

import com.web.admin.DTO.DeviceDTO;
import com.web.admin.DTO.RolesDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DeviceDAO {

    List<DeviceDTO> getDeviceList();
    List<RolesDTO> getRolesList();
    DeviceDTO getSelectDevice(String name);
    DeviceDTO getDevice();

    void insertDevice(DeviceDTO deviceDTO);
    void updateDevice(DeviceDTO deviceDTO);
    void deleteDevice(String name);
}

package com.web.admin.service.api;

import com.web.admin.DAO.DeviceDAO;
import com.web.admin.DTO.DeviceDTO;
import com.web.admin.DTO.api.getNetTrafficInfo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TrafficInfoService {
    private final DeviceDAO deviceDAO;
    public GetNetTrafficInfoAPI getNetTrafficInfoAPI(){
        RestTemplate restTemplate = new RestTemplate();
        DeviceDTO device = deviceDAO.getDevice();
        JSONObject jsonObject = new JSONObject(restTemplate.getForObject(device.getIp() + "/system/traffic_info", String.class));
        GetNetTrafficInfoAPI getNetTrafficInfoAPI = new GetNetTrafficInfoAPI();
        log.info("jsonObject : " + jsonObject);

        JSONObject traffic_Info = jsonObject.getJSONObject("traffic_Info");
        JSONArray arr_traffic_Info_interface = traffic_Info.getJSONArray("interface");

        TrafficInfoAPI trafficInfoAPI = new TrafficInfoAPI();
        List<InterfaceAPI> interfaceAPIList = new ArrayList<InterfaceAPI>();

        for(int i=0; i<arr_traffic_Info_interface.length(); i++){
            JSONObject traffic_Info_interface = arr_traffic_Info_interface.getJSONObject(i);
            JSONArray arr_source_list = traffic_Info_interface.getJSONArray("source_list");
            InterfaceAPI interfaceAPI = new InterfaceAPI();
            List<SourceListAPI> sourceListAPIList = new ArrayList<SourceListAPI>();

            for(int j=0; j<arr_source_list.length(); j++){
                JSONObject source_list = arr_source_list.getJSONObject(j);
                JSONArray arr_trak_list = source_list.getJSONArray("trak_list");
                SourceListAPI sourceListAPI = new SourceListAPI();
                List<TrakListAPI> trakListAPIList = new ArrayList<TrakListAPI>();

                for(int k=0; k<arr_trak_list.length(); k++){
                    JSONObject trak_list = arr_trak_list.getJSONObject(k);
                    TrakListAPI trakListAPI = new TrakListAPI();
                    if(trak_list.has("type") && !trak_list.getString("type").isEmpty())
                        trakListAPI.setType(trak_list.getString("type"));
                    if(trak_list.has("id") && !trak_list.getString("id").isEmpty())
                        trakListAPI.setId(trak_list.getInt("id"));
                    if(trak_list.has("codec") && !trak_list.getString("codec").isEmpty())
                        trakListAPI.setCodec(trak_list.getString("codec"));
                    if(trak_list.has("samplerate") && !trak_list.getString("samplerate").isEmpty())
                        trakListAPI.setSamplerate(trak_list.getInt("samplerate"));
                    if(trak_list.has("channel") && !trak_list.getString("channel").isEmpty())
                        trakListAPI.setChannel(trak_list.getFloat("channel"));
                    if(trak_list.has("bitrate") && !trak_list.getString("bitrate").isEmpty())
                        trakListAPI.setBitrate(trak_list.getInt("bitrate"));
                    if(trak_list.has("language") && !trak_list.getString("language").isEmpty())
                        trakListAPI.setLanguage(trak_list.getString("language"));
                    if(trak_list.has("profile") && !trak_list.getString("profile").isEmpty())
                        trakListAPI.setProfile(trak_list.getString("profile"));
                    if(trak_list.has("level") && !trak_list.getString("level").isEmpty())
                        trakListAPI.setLevel(trak_list.getFloat("level"));
                    if(trak_list.has("width") && !trak_list.getString("width").isEmpty())
                        trakListAPI.setWidth(trak_list.getInt("width"));
                    if(trak_list.has("height") && !trak_list.getString("height").isEmpty())
                        trakListAPI.setHeight(trak_list.getInt("height"));
                    if(trak_list.has("fps") && !trak_list.getString("fps").isEmpty())
                        trakListAPI.setFps(trak_list.getFloat("fps"));
                    if(trak_list.has("bit_depth") && !trak_list.getString("bit_depth").isEmpty())
                        trakListAPI.setBit_depth(trak_list.getInt("bit_depth"));
                    if(trak_list.has("color") && !trak_list.getString("color").isEmpty())
                        trakListAPI.setColor(trak_list.getString("color"));
                    trakListAPIList.add(trakListAPI);
                }
                if(source_list.has("src_addr") && !source_list.getString("src_addr").isEmpty())
                    sourceListAPI.setSrc_addr(source_list.getString("src_addr"));
                if(source_list.has("dst_addr") && !source_list.getString("dst_addr").isEmpty())
                    sourceListAPI.setDst_addr(source_list.getString("dst_addr"));
                if(source_list.has("total_bitrate") && !source_list.getString("total_bitrate").isEmpty())
                    sourceListAPI.setTotal_bitrate(source_list.getInt("total_bitrate"));
                if(source_list.has("menu") && !source_list.getString("menu").isEmpty())
                    sourceListAPI.setMenu(source_list.getInt("menu"));
                if(source_list.has("title") && !source_list.getString("title").isEmpty())
                    sourceListAPI.setTitle(source_list.getString("title"));
                sourceListAPI.setTrak_list(trakListAPIList);
                sourceListAPIList.add(sourceListAPI);
            }
            if(traffic_Info_interface.has("name") && !traffic_Info_interface.getString("name").isEmpty())
                interfaceAPI.setName(traffic_Info_interface.getString("name"));
            interfaceAPI.setSource_list(sourceListAPIList);
            interfaceAPIList.add(interfaceAPI);
        }
        trafficInfoAPI.setInterface_list(interfaceAPIList);
        getNetTrafficInfoAPI.setTraffic_Info(trafficInfoAPI);

        log.info("trafficInfoAPI : " + getNetTrafficInfoAPI);

        return getNetTrafficInfoAPI;
    }
}

package com.web.admin.controller;

import com.web.admin.DAO.DeviceDAO;
import com.web.admin.DTO.DeviceDTO;
import com.web.admin.DTO.UserDTO;
import com.web.admin.DTO.UserLogDTO;
import com.web.admin.DTO.api.getStatus.idolLive.GetStatusAPI;
import com.web.admin.DTO.api.getNetTrafficInfo.GetNetTrafficInfoAPI;
import com.web.admin.DTO.api.notifyingEvent.NotifyingEventAPI;
import com.web.admin.DTO.api.resync.ResyncAPI;
import com.web.admin.DTO.api.resync.ResyncInfoAPI;
import com.web.admin.DTO.api.transcodingStandby.TranscodingStandbyAPI;
import com.web.admin.DTO.api.transcodingStartStop.TranscodingStartAPI;
import com.web.admin.DTO.api.transcodingStartStop.TranscodingStopAPI;
import com.web.admin.service.UserLogService;
import com.web.admin.service.api.*;
import org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class APIController {
    private final TransCodingStandbyService transCodingStandbyService;
    private final TransCoderEventService transCoderEventService;
    private final ResyncTimestampService resyncTimestampService;
    private final GettingStatusService gettingStatusService;
    private final UserLogService userLogService;
    private final TrafficInfoService trafficInfoService;
    private final DeviceDAO deviceDAO;
    @GetMapping("/system/traffic_info")
    public GetNetTrafficInfoAPI getNetTrafficInfoAPI(Number analysis_time){
        return trafficInfoService.getNetTrafficInfoAPI();
    }

    @PutMapping("transcode/standby")
    public ResponseEntity<String> transcodingStandbyAPI(UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        TranscodingStandbyAPI standBy = new TranscodingStandbyAPI();
        RestTemplate restTemplate = new RestTemplate();
        DeviceDTO device = transCodingStandbyService.getDevice();
        standBy = transCodingStandbyService.getTranscodingStandbyAPI(device.getRole());
        JSONObject JSONstandBy = new JSONObject(standBy);
        log.info(JSONstandBy+"");
        ResponseEntity<String> result = restTemplate.exchange(device.getIp() + "/transcode/standby", HttpMethod.PUT, new HttpEntity<>(standBy), String.class, standBy);
        log.info(result+"");
        log.info(result.getBody()+"");

        if (result.getStatusCodeValue()==200) {
            userLogDTO.setLogString("Device StandBy");
        }else{
            userLogDTO.setLogString("Device 연결 실패(1)");
        }
        userLogService.getUserLog(userDTO,userLogDTO);

        return result;
    }

    @GetMapping("transcode/status")
    public GetStatusAPI getStatusAPI() throws IOException {
        return gettingStatusService.getStatusAPI();
    }


    @PutMapping("transcode/start")
    public TranscodingStartAPI transcodingStartAPI(UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        TranscodingStartAPI start = new TranscodingStartAPI();
        RestTemplate restTemplate = new RestTemplate();
        DeviceDTO device = transCodingStandbyService.getDevice();
        ResponseEntity<String> result = restTemplate.exchange(device.getIp() + "/transcode/start", HttpMethod.PUT, new HttpEntity<>(start), String.class);
        log.info(result+"");

        if (result.getStatusCodeValue()==200) {
            userLogDTO.setLogString("Device Start");
        }else{
            userLogDTO.setLogString("Device 연결 실패(2)");
        }
        userLogService.getUserLog(userDTO,userLogDTO);

        return new TranscodingStartAPI();
    }

    @PutMapping("transcode/stop")
    public TranscodingStopAPI transcodingStopAPI(UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        TranscodingStopAPI stop = new TranscodingStopAPI();
        RestTemplate restTemplate = new RestTemplate();
        DeviceDTO device = transCodingStandbyService.getDevice();
        ResponseEntity<String> result = restTemplate.exchange(device.getIp() + "/transcode/stop", HttpMethod.PUT, new HttpEntity<>(stop), String.class);
        log.info(result+"");

        if (result.getStatusCodeValue()==200) {
            userLogDTO.setLogString("Device Stop");
        }else{
            userLogDTO.setLogString("Device 연결 실패(3)");
        }
        userLogService.getUserLog(userDTO,userLogDTO);

        return new TranscodingStopAPI();
    }

    @PutMapping("transcode/event")
    public void notifyingEventAPI(@RequestBody NotifyingEventAPI data){
        log.info(data+"");
        transCoderEventService.insertTransCoderEvent(data);
    }

    @PostMapping("/transcode/resynchronize")
    public ModelAndView resyncAPI(ResyncInfoAPI resyncInfoAPI, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        ModelAndView mav = new ModelAndView("alert");

        ResyncAPI resyncAPI = new ResyncAPI();
        RestTemplate restTemplate = new RestTemplate();
        DeviceDTO device = transCodingStandbyService.getDevice();
        ResponseEntity<String> result = restTemplate.exchange( device.getIp()+"/transcode/resynchronize", HttpMethod.PUT, new HttpEntity<>(resyncAPI), String.class, resyncTimestampService.getResyncTimestamp(resyncInfoAPI));
        log.info(resyncTimestampService.getResyncTimestamp(resyncInfoAPI)+"보내는 데이터!!!!!!!!!!!!!!!!!!!!!!!");
        log.info(result+"resynchronize 호출!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");

        if (result.getStatusCodeValue()==200) {
            userLogDTO.setLogString("resynchronize 설정 저장");
            mav.addObject("msg", "설정 저장에 성공하였습니다.");
        }else{
            userLogDTO.setLogString("resynchronize 설정 저장 실패");
            mav.addObject("msg", "설정 저장에 실패하였습니다.");
        }

        userLogService.getUserLog(userDTO,userLogDTO);
        mav.addObject("url", "../dashboard/monitor/detail");

        return mav;
    }

    @PutMapping("/system/healthcheck")
    public void healthCheck(){
        log.info("healthCheck 호출!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @PutMapping("/system/hellonmvt")
    public int helloNmvt(String address){

        HttpHeaders header = new HttpHeaders();
        RestTemplate restTemplate = new ApiRestTemplate(3);
        ResponseEntity<String> result = restTemplate.exchange( address+"/system/hellonmvt", HttpMethod.PUT, new HttpEntity<>(header), String.class);
        log.info(result.getStatusCodeValue()+"hellonmvt 호출!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return result.getStatusCodeValue();
    }

    //  restTemplate 설정 저장
   public class ApiRestTemplate extends RestTemplate {
       public ApiRestTemplate(int readTimeout) {

                SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

                factory.setConnectTimeout(1000);
                factory.setReadTimeout(readTimeout * 1000);
                setRequestFactory(factory);
            }
        }
}

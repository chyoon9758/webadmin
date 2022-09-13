package com.web.admin.controller;


import com.web.admin.DAO.DeviceDAO;
import com.web.admin.DTO.api.notifyingEvent.IdolLiveEventAPI;
import com.web.admin.DTO.api.transcodingStandby.TranscodingStandbyAPI;
import com.web.admin.service.api.GettingStatusService;
import com.web.admin.service.api.TransCoderEventService;
import com.web.admin.service.api.TransCodingStandbyService;
import com.web.admin.DTO.*;
import com.web.admin.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashBoardController {

    @Value("${user.logcount}")
    private int userlogcnt;
    @Value("${transcoder.logcount}")
    private int transcoderlogcnt;
    @Value("${interval.timeout}")
    private int intervaltimeout;
    private final MonitorService monitorService;
    private final DeviceService deviceService;
    private final UserLogService userLogService;
    private final ChannelInputService channelInputService;
    private final ResizeService resizeService;
    private final PresetVideoService presetVideoService;
    private final PackagerService packagerService;
    private final ComposerService composerService;
    private final RolesService rolesService;
    private final TransCodingStandbyService transCodingStandbyService;
    private final TransCoderEventService transCoderEventService;
    private final GettingStatusService gettingStatusService;
    private final DeviceDAO deviceDAO;

    @GetMapping("/monitor/main")
    public ModelAndView Monitor_Main(@AuthenticationPrincipal UserDTO userDTO) throws IOException {
        ModelAndView mav = new ModelAndView("monitor/main");
        DeviceDTO deviceDTO;
        TranscodingStandbyAPI standBy;
        RestTemplate restTemplate = new RestTemplate();
        try{
            standBy = transCodingStandbyService.getTranscodingStandbyAPI(transCodingStandbyService.getDevice().getRole());
            mav.addObject("standBy", standBy);
        }catch (Exception e){
            log.info("Role을 온전히 불러오지 못함" + e);
        }finally {

        }
        try{
            deviceDTO = transCodingStandbyService.getDevice();
            mav.addObject("Device", deviceDTO);
        }catch (Exception e){
            log.info("Device를 온전히 불러오지 못함");
        }finally {

        }
        mav.addObject("User", userDTO);
        mav.addObject("userlog",userLogService.getListAll(userlogcnt));
        mav.addObject("transcoderlog", transCoderEventService.getListAll(transcoderlogcnt));
        mav.addObject("StatusAPI", gettingStatusService.getStatusAPI());
        mav.addObject("intervaltimeout", intervaltimeout);

        return mav;
    }

    @GetMapping("/monitor/main/getTranscoderEvent")
    @ResponseBody
    public List<IdolLiveEventAPI> getTranscoderEvent(@AuthenticationPrincipal UserDTO userDTO){
        return transCoderEventService.getListAll(transcoderlogcnt);
    }

    @GetMapping("/monitor/main/getActionEvent")
    @ResponseBody
    public List<UserLogDTO> getActionEvent(@AuthenticationPrincipal UserDTO userDTO){
        return userLogService.getListAll(userlogcnt);
    }

    @GetMapping("/monitor/detail")
    public ModelAndView Monitor_Detail(@AuthenticationPrincipal UserDTO userDTO) throws IOException {
        DeviceDTO device = deviceDAO.getDevice();

        ModelAndView mav = new ModelAndView("monitor/detail");
        mav.addObject("User", userDTO);
        mav.addObject("list",monitorService.getInputByGroupId());
        mav.addObject("domain", device.getIp());
        mav.addObject("StatusAPI", gettingStatusService.getStatusAPI());
        mav.addObject("intervaltimeout", intervaltimeout);

        return mav;
    }

    @GetMapping("/config/device")
    public ModelAndView Config_Device(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(required = false) String name) throws IOException {
        ModelAndView mav = new ModelAndView("config/device");
        mav.addObject("name",name);
        mav.addObject("User", userDTO);
        mav.addObject("devicelist",deviceService.getDeviceList());
        mav.addObject("roleslist",deviceService.getRolesList());
        mav.addObject("selectdevice",deviceService.getSelectDevice(name));
        mav.addObject("StatusAPI", gettingStatusService.getStatusAPI());
        mav.addObject("intervaltimeout", intervaltimeout);

        return mav;
    }
    @PostMapping("/config/device/insert.do")
    public String Conifg_Device_Insert(@ModelAttribute DeviceDTO deviceDTO, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        try{
            deviceService.insertDevice(deviceDTO);
            model.addAttribute("msg", "데이터를 성공적으로 저장하였습니다.");
            model.addAttribute("url", "../device");
            userLogDTO.setLogString("Device 설정 저장");
        }
        catch (Exception e){
            model.addAttribute("msg", "데이터 저장에 실패하였습니다.");
            model.addAttribute("url", "../device");
            userLogDTO.setLogString("Device 설정 저장 실패");
        }
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @PostMapping("/config/device/update.do")
    public String Conifg_Device_Update(@ModelAttribute DeviceDTO deviceDTO, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        try{
            deviceService.updateDevice(deviceDTO);
            model.addAttribute("msg", "데이터를 성공적으로 수정하였습니다.");
            model.addAttribute("url", "../device");
            userLogDTO.setLogString("Device 설정 수정");
        }
        catch (Exception e){
            model.addAttribute("msg", "데이터 저장에 실패하였습니다.");
            model.addAttribute("url", "../device");
            userLogDTO.setLogString("Device 설정 저장 실패");
        }
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @PostMapping("/config/device/delete.do")
    public String Conifg_Device_Delete(@ModelAttribute DeviceDTO deviceDTO, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        deviceService.deleteDevice(deviceDTO.getName());
        model.addAttribute("msg", "데이터 삭제에 성공하였습니다.");
        model.addAttribute("url", "../device");
        userLogDTO.setLogString("Device 설정 삭제");
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @GetMapping("/config/roles")
    public ModelAndView Config_Roles(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(required = false) String roles_id, RolesDTO rolesDTO, String fix) throws IOException {
        ModelAndView mav = new ModelAndView("config/roles");

        mav.addObject("roles_id", roles_id);
        mav.addObject("fix", fix);
        mav.addObject("input_streams", rolesDTO.getInput_streams());

        if(roles_id!=null) {
            mav.addObject("preset_group_id_1_1", rolesService.getPresetGroupId(roles_id, "1+1"));
            mav.addObject("preset_group_id_2_1", rolesService.getPresetGroupId(roles_id, "thumbnail"));
            if(rolesDTO.getInput_streams()==3||rolesDTO.getInput_streams()==4||rolesDTO.getInput_streams()==5||rolesDTO.getInput_streams()==6||rolesDTO.getInput_streams()==7){
                mav.addObject("preset_group_id_1_2", rolesService.getPresetGroupId(roles_id, "1+2"));
                if(rolesDTO.getInput_streams()==4||rolesDTO.getInput_streams()==5||rolesDTO.getInput_streams()==6){
                    mav.addObject("preset_group_id_1_3", rolesService.getPresetGroupId(roles_id, "1+3"));
                }
            }
        }

        mav.addObject("User", userDTO);
        mav.addObject("getSelectRoles", rolesService.getSelectRoles(roles_id));
        mav.addObject("getListRoles", rolesService.getListRoles());

        mav.addObject("getListInput", rolesService.getListInput());
        mav.addObject("getListComposer", rolesService.getListComposer());
        mav.addObject("getListPackager", rolesService.getListPackager());
        mav.addObject("getListVideo", rolesService.getListVideo());
        mav.addObject("getListResizer", rolesService.getListResizer());
        mav.addObject("getListAbrResizer", rolesService.getListAbrResizer());
        mav.addObject("StatusAPI", gettingStatusService.getStatusAPI());
        mav.addObject("intervaltimeout", intervaltimeout);

        return mav;
    }
    //input_streams 값 가져오기
    @ResponseBody
    @PostMapping("/config/roles/putInputStreams.do")
    public String put_InputStreams(@RequestParam("group_id") String group_id){
        return rolesService.getStreamsInput(group_id);
    }
    //input_item_id 값 가져오기
    @ResponseBody
    @PostMapping("/config/roles/getSourceId.do")
    public List<ChannelInputDTO> get_SourceId(@RequestParam("input_id") String input_id){
        return rolesService.getListSourceId(input_id);
    }
    //resize_item_id 값 가져오기
    @ResponseBody
    @PostMapping("/config/roles/getResizerId.do")
    public List<ResizeDTO> get_ResizerId(@RequestParam("resize_id") String resize_id){
        return rolesService.getListResizerId(resize_id);
    }
    //Composer_item_id 값 가져오기
    @ResponseBody
    @PostMapping("/config/roles/getComposerId.do")
    public List<ComposerDTO> get_ComposerId(@RequestParam("composer_id") String composer_id){
        return rolesService.getListComposerId(composer_id);
    }
    //Abr_item_id 값 가져오기
    @ResponseBody
    @PostMapping("/config/roles/getAbrResizerId.do")
    public List<ResizeDTO> get_AbrResizerId(@RequestParam("abr_id") String abr_id){
        return rolesService.getListAbrResizerId(abr_id);
    }
    //Video_item_id 값 가져오기
    @ResponseBody
    @PostMapping("/config/roles/getVideoId.do")
    public List<PresetVideoDTO> get_VideoId(@RequestParam("video_id") String video_id){
        return rolesService.getListVideoId(video_id);
    }
    @PostMapping("/config/roles/update.do")
    public String Conifg_Roles_Update(@ModelAttribute RolesDTO rolesDTO,@ModelAttribute RolesProcessDTO rolesProcessDTO, Model model, String query, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        String result = rolesService.insertRoles(rolesDTO,rolesProcessDTO,query);
        if(result=="update"){
            model.addAttribute("msg", "데이터를 성공적으로 수정하였습니다.");
            model.addAttribute("url", "../roles");
            userLogDTO.setLogString("Roles 설정 수정");
        }else if(result=="insert"){
            model.addAttribute("msg", "데이터를 성공적으로 저장하였습니다.");
            model.addAttribute("url", "../roles");
            userLogDTO.setLogString("Roles 설정 저장");
        }else if(result=="fail"){
            model.addAttribute("msg", "데이터 저장에 실패하였습니다.");
            model.addAttribute("url", "../roles");
            userLogDTO.setLogString("Roles 설정 저장 실패");
        }
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @ResponseBody
    @PostMapping("/config/roles/chk.do")
    public RolesDTO Roles_Chk_Id(@ModelAttribute RolesDTO rolesDTO){
        return rolesService.getSelectRoles(rolesDTO.getId());
    }
    @PostMapping("/config/roles/delete.do")
    public String Conifg_Roles_Delete(@ModelAttribute RolesDTO rolesDTO, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        rolesService.deleteRolesId(rolesDTO.getId());
        rolesService.deletePresetId(rolesDTO.getId());
        model.addAttribute("msg", "데이터 삭제에 성공하였습니다.");
        model.addAttribute("url", "../roles");
        userLogDTO.setLogString("Roles 설정 삭제");
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @GetMapping("/config/packager")
    public String Config_Packager(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(required = false) String group_id, String fix, Model model) throws IOException {
        model.addAttribute("User", userDTO);
        model.addAttribute("group_id", group_id);
        model.addAttribute("fix", fix);
        model.addAttribute("getListAll", packagerService.getListAll());
        model.addAttribute("getPackagerByGroupId", packagerService.getPackagerByGroupId(group_id));
        model.addAttribute("StatusAPI", gettingStatusService.getStatusAPI());
        model.addAttribute("intervaltimeout", intervaltimeout);

        return "config/packager";
    }
    @PostMapping("config/packager")
    public String Config_Packager_Update(PackagerDTO packagerDTO, String query, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        String result = packagerService.updateRow(packagerDTO, query);

        if(result=="duplicate"){
            model.addAttribute("msg", "group_id가 중복입니다.");
            model.addAttribute("url", "packager");
            userLogDTO.setLogString("Packager 설정 저장 실패");
        } else if (result=="fail") {
            model.addAttribute("msg", "데이터 저장에 실패하였습니다.");
            model.addAttribute("url", "packager");
            userLogDTO.setLogString("Packager 설정 저장 실패");
        } else if (result=="update") {
            model.addAttribute("msg", "데이터 수정에 성공하였습니다.");
            model.addAttribute("url", "packager");
            userLogDTO.setLogString("Packager 설정 수정");
        } else if (result=="insert") {
            model.addAttribute("msg", "데이터 저장에 성공하였습니다.");
            model.addAttribute("url", "packager");
            userLogDTO.setLogString("Packager 설정 저장");
        }
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @GetMapping("/config/packager/delete")
    public String Config_Packager_Delete(@AuthenticationPrincipal UserDTO userDTO, @RequestParam String group_id, UserLogDTO userLogDTO, Model model) throws IOException {
        packagerService.deleteByGroupId(group_id);
        model.addAttribute("msg", "데이터 삭제에 성공하였습니다.");
        model.addAttribute("url", "../packager");
        model.addAttribute("StatusAPI", gettingStatusService.getStatusAPI());
        userLogDTO.setLogString("packager 설정 삭제");
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @ResponseBody
    @PostMapping("/config/packager/chk.do")
    public PackagerDTO Packager_Chk_GroupId(PackagerDTO packagerDTO){
        return packagerService.getPackagerByGroupId(packagerDTO.getGroup_id());
    }
    @GetMapping("/config/composer")
    public String Config_Composer(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(required = false) String group_id, String fix, Model model) throws IOException {
        model.addAttribute("User", userDTO);
        model.addAttribute("group_id", group_id);
        model.addAttribute("fix", fix);
        model.addAttribute("getListGroupConcat", composerService.getListGroupConcat());
        model.addAttribute("getComposerByGroupId", composerService.getComposerByGroupId(group_id));
        model.addAttribute("StatusAPI", gettingStatusService.getStatusAPI());
        model.addAttribute("intervaltimeout", intervaltimeout);

        return "config/composer";
    }
    @PostMapping("/config/composer")
    public String Config_Composer_Update(ComposerDTO composerDTO, int[] width, int[] height, String[] left, String[] top, String[] right, String[] bottom, String query, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        String result = composerService.updateComposer(composerDTO, width, height, left, top, right, bottom, query);
        if(result=="duplicate"){
            model.addAttribute("msg", "group_id가 중복입니다.");
            model.addAttribute("url", "composer");
            userLogDTO.setLogString("Composer 설정 저장 실패");
        } else if (result=="fail") {
            model.addAttribute("msg", "데이터 저장에 실패하였습니다.");
            model.addAttribute("url", "composer");
            userLogDTO.setLogString("Composer 설정 저장 실패");
        } else if (result=="update") {
            model.addAttribute("msg", "데이터 수정에 성공하였습니다.");
            model.addAttribute("url", "composer");
            userLogDTO.setLogString("Composer 설정 수정");
        } else if (result=="insert") {
            model.addAttribute("msg", "데이터 저장에 성공하였습니다.");
            model.addAttribute("url", "composer");
            userLogDTO.setLogString("Composer 설정 저장");

        }
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @GetMapping("/config/composer/delete")
    public String Config_Composer_Delete(@AuthenticationPrincipal UserDTO userDTO, @RequestParam String group_id, UserLogDTO userLogDTO, Model model) throws IOException {
        composerService.deleteListByGroupId(group_id);
        model.addAttribute("msg", "데이터 삭제에 성공하였습니다.");
        model.addAttribute("url", "../composer");
        model.addAttribute("StatusAPI", gettingStatusService.getStatusAPI());
        userLogDTO.setLogString("Composer 설정 삭제");
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @ResponseBody
    @PostMapping("/config/composer/chk.do")
    public List<ComposerDTO> Composer_Chk_GroupId(ComposerDTO composerDTO){
        return composerService.getComposerByGroupId(composerDTO.getGroup_id());
    }
    @GetMapping("/config/resize")
    public String Config_Resize(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(required = false) String group_id, String fix, Model model) throws IOException {
        model.addAttribute("User", userDTO);
        model.addAttribute("group_id", group_id);
        model.addAttribute("fix", fix);
        model.addAttribute("getListGroupConcat", resizeService.getListGroupConcat());
        model.addAttribute("getResizeByGroupId", resizeService.getResizeByGroupId(group_id));
        model.addAttribute("StatusAPI", gettingStatusService.getStatusAPI());
        model.addAttribute("intervaltimeout", intervaltimeout);

        return "config/resize";
    }
    @PostMapping("/config/resize")
    public String Config_Resize_Update(ResizeDTO resizeDTOList, int width[], int height[], String query, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        String result = resizeService.updateResize(resizeDTOList, width, height, query);

        if(result=="duplicate"){
            model.addAttribute("msg", "group_id가 중복입니다.");
            model.addAttribute("url", "resize");
            userLogDTO.setLogString("Resuze 설정 저장 실패");
        } else if (result=="fail") {
            model.addAttribute("msg", "데이터 저장에 실패하였습니다.");
            model.addAttribute("url", "resize");
            userLogDTO.setLogString("Resuze 설정 저장 실패");
        } else if (result=="update") {
            model.addAttribute("msg", "데이터 수정에 성공하였습니다.");
            model.addAttribute("url", "resize");
            userLogDTO.setLogString("Resuze 설정 수정");
        } else if (result=="insert") {
            model.addAttribute("msg", "데이터 저장에 성공하였습니다.");
            model.addAttribute("url", "resize");
            userLogDTO.setLogString("Resize 설정 저장");
        }
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @GetMapping("/config/resize/delete")
    public String Config_Resize_Detele(@AuthenticationPrincipal UserDTO userDTO, @RequestParam String group_id, UserLogDTO userLogDTO, Model model) throws IOException {
        resizeService.deleteListByGroupId(group_id);
        model.addAttribute("msg", "데이터 삭제에 성공하였습니다.");
        model.addAttribute("url", "../resize");
        model.addAttribute("StatusAPI", gettingStatusService.getStatusAPI());
        userLogDTO.setLogString("Resize 설정 삭제");
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @ResponseBody
    @PostMapping("/config/resize/chk.do")
    public List<ResizeDTO> Resize_Chk_GroupId(ResizeDTO resizeDTO){
        return resizeService.getResizeByGroupId(resizeDTO.getGroup_id());
    }
    @GetMapping("/config/input")
    public String Config_Input(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(required = false) String group_id, String fix, Model model) throws IOException {
        model.addAttribute("User", userDTO);
        model.addAttribute("group_id", group_id);
        model.addAttribute("fix", fix);
        model.addAttribute("getListGroupConcat",channelInputService.getListGroupConcat());
        model.addAttribute("getInputByGroupId",channelInputService.getInputByGroupId(group_id));
        model.addAttribute("StatusAPI", gettingStatusService.getStatusAPI());
        model.addAttribute("intervaltimeout", intervaltimeout);
        return "config/input";
    }
    @PostMapping("/config/input")
    public String Config_Input_Update(ChannelInputDTO channelInputDTOList, String query, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){
        String result = channelInputService.updateChannelInput(channelInputDTOList, query);

        if(result=="duplicate"){
            model.addAttribute("msg", "group_id가 중복입니다.");
            model.addAttribute("url", "input");
            userLogDTO.setLogString("Input 설정 저장 실패");
        } else if (result=="fail") {
            model.addAttribute("msg", "데이터 저장에 실패하였습니다.");
            model.addAttribute("url", "input");
            userLogDTO.setLogString("Input 설정 저장 실패");
        } else if (result=="update") {
            model.addAttribute("msg", "데이터 수정에 성공하였습니다.");
            model.addAttribute("url", "input");
            userLogDTO.setLogString("Input 설정 수정");
        } else if (result=="insert") {
            model.addAttribute("msg", "데이터 저장에 성공하였습니다.");
            model.addAttribute("url", "input");
            userLogDTO.setLogString("Input 설정 저장");
        }
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @ResponseBody
    @PostMapping("/config/input/chk.do")
    public List<ChannelInputDTO> Input_Chk_Groupid(ChannelInputDTO channelInputDTO){
        return channelInputService.getInputByGroupId(channelInputDTO.getGroup_id());
    }
    @GetMapping("/config/input/delete")
    public String Config_Input_Detele(@AuthenticationPrincipal UserDTO userDTO, @RequestParam String group_id, UserLogDTO userLogDTO, Model model){
        channelInputService.deleteListByGroupId(group_id);
        model.addAttribute("msg", "데이터 삭제에 성공하였습니다.");
        model.addAttribute("url", "../input");
        userLogDTO.setLogString("Input 설정 삭제");
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }
    @GetMapping("/config/video")
    public ModelAndView Config_Video(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(required = false) String group_id,@RequestParam(required = false) String fix) throws IOException {
        ModelAndView mav = new ModelAndView("config/video");

        mav.addObject("User", userDTO);
        mav.addObject("group_id", group_id);
        mav.addObject("fix", fix);
        mav.addObject("data", presetVideoService.getInputByData(group_id));
        mav.addObject("getListAll", presetVideoService.getListAll());
        mav.addObject("getInputByPresetId", presetVideoService.getInputByPresetId(group_id));
        mav.addObject("getListGroupConcat", presetVideoService.getListGroupConcat());
        mav.addObject("StatusAPI", gettingStatusService.getStatusAPI());
        mav.addObject("intervaltimeout", intervaltimeout);
        return mav;
    }
    @ResponseBody
    @PostMapping("/config/video/chk.do")
    public List<PresetVideoDTO> Chk_GroupId(@ModelAttribute PresetVideoDTO presetVideoDTO, String group_id){
        return presetVideoService.getInputByPresetId(group_id);
    }
    @PostMapping("/config/video/insert.do")
    public String Insert_Video(@ModelAttribute PresetVideoDTO presetVideoDTO, HttpServletRequest request, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){

        try{
            presetVideoService.insertVideoData(request, presetVideoDTO);

            model.addAttribute("msg", "데이터를 성공적으로 저장하였습니다.");
            model.addAttribute("url", "../video");
            userLogDTO.setLogString("Video 설정 저장");
        }catch (Exception e){

            model.addAttribute("msg", "데이터 저장에 실패하였습니다.");
            model.addAttribute("url", "../video");
            userLogDTO.setLogString("Video 설정 저장 실패");
        }
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }

    @PostMapping("/config/video/update.do")
    public String Update_Video(@ModelAttribute PresetVideoDTO presetVideoDTO, HttpServletRequest request, String group_id, String create_time, int selected, Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){

        try{
            presetVideoService.deletePresetId(presetVideoDTO.getGroup_id());
            presetVideoService.insertVideoData(request, presetVideoDTO);

            model.addAttribute("msg", "데이터를 성공적으로 수정하였습니다.");
            model.addAttribute("url", "../video");
            userLogDTO.setLogString("Video 설정 수정");
        }catch (Exception e){

            model.addAttribute("msg", "데이터 수정에 실패하였습니다.");
            model.addAttribute("url", "../video");
            userLogDTO.setLogString("Video 설정 저장 실패");
        }
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }

    @PostMapping("/config/video/delete.do")
    public String Delete_Video(@ModelAttribute PresetVideoDTO presetVideoDTO, String group_id, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO, Model model){
        presetVideoService.deletePresetId(group_id);
        model.addAttribute("msg", "데이터 삭제에 성공하였습니다.");
        model.addAttribute("url", "../video");
        userLogDTO.setLogString("Video 설정 삭제");
        userLogService.getUserLog(userDTO,userLogDTO);
        return "alert";
    }

}

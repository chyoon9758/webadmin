package com.web.admin.service;

import com.web.admin.DAO.RolesDAO;
import com.web.admin.DTO.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RolesService {
    private final RolesDAO rolesDAO;

    public List<RolesDTO> getListRoles() { return rolesDAO.getListRoles();}
    public RolesDTO getSelectRoles(String id) { return rolesDAO.getSelectRoles(id);}
    public List<ChannelInputDTO> getListInput() { return rolesDAO.getListInput();}
    public List<ChannelInputDTO> getListSourceId(String group_id) { return rolesDAO.getListSourceId(group_id);}
    public List<ComposerDTO> getListComposer() { return rolesDAO.getListComposer();}
    public List<ComposerDTO> getListComposerId(String group_id) { return rolesDAO.getListComposerId(group_id);}
    public List<PackagerDTO> getListPackager() { return rolesDAO.getListPackager();}
    public List<PresetVideoDTO> getListVideo() { return rolesDAO.getListVideo();}
    public List<PresetVideoDTO> getListVideoId(String group_id) { return rolesDAO.getListVideoId(group_id);}
    public List<ResizeDTO> getListResizer() { return rolesDAO.getListResizer();}
    public List<ResizeDTO> getListResizerId(String group_id) { return rolesDAO.getListResizerId(group_id);}
    public List<ResizeDTO> getListAbrResizer() { return rolesDAO.getListAbrResizer();}
    public List<ResizeDTO> getListAbrResizerId(String group_id) { return rolesDAO.getListAbrResizerId(group_id);}
    public String getStreamsInput(String group_id){ return rolesDAO.getStreamsInput(group_id);}
    public List<HashMap<String, String>> getPresetGroupId(String group_id, String id) {

        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        list = rolesDAO.getPresetGroupId(group_id,id);

        HashMap<String, String> map = new HashMap<String, String>();

        String item_id = "";
        String source_id = "";
        String resizer_id = "";
        String text = "pre_grid_process_";

        // PresetInfoDTO 중 grid_process_'i' 크기만을 두기 위해 -5를함 (group_id, preset_group_id, id, grid_composer, abr_resizer, encoder)
        for(int j=1; j<list.get(0).size()-5; j++){
            item_id += list.get(0).get(text+j).split(",")[0];
            source_id += list.get(0).get(text+j).split(",")[1];
            resizer_id += list.get(0).get(text+j).split(",")[2];

            if(j<list.get(0).size()-6){
                item_id += ",";
                source_id += ",";
                resizer_id += ",";
            }
        }

        map.put("id",item_id);
        map.put("source_id",source_id);
        map.put("resizer_id",resizer_id);
        map.put("grid_composer",list.get(0).get("grid_composer"));
        map.put("abr_resizer",list.get(0).get("abr_resizer"));
        map.put("encoder",list.get(0).get("encoder"));

        List<HashMap<String, String>> list1 = new ArrayList<HashMap<String, String>>();

        list1.add(map);

        return list1;
    }

    public String insertRoles(RolesDTO rolesDTO, RolesProcessDTO rolesProcessDTO, String query){

        List<HashMap<String, String>> rolesProcessDTOlist = new ArrayList<HashMap<String, String>>();

        HashMap<String,String> map1 = new HashMap<String, String>();
        HashMap<String,String> map2 = new HashMap<String, String>();
        HashMap<String,String> map3 = new HashMap<String, String>();
        HashMap<String,String> map4 = new HashMap<String, String>();
        String text = "pre_grid_process_";

        String[] Process_1_1 = rolesProcessDTO.getProcess_1_1().split(",");
        String[] Source_1_1 = rolesProcessDTO.getSource_1_1().split(",");
        String[] Resize_1_1 = rolesProcessDTO.getResize_1_1().split(",");
        for(int i=1; i<Process_1_1.length+1; i++){
            map1.put(text+i,Process_1_1[i-1]+","+Source_1_1[i-1]+","+Resize_1_1[i-1]);
        }
        map1.put("group_id",rolesDTO.getId());
        map1.put("preset_group_id",rolesDTO.getPreset_group_id_1());
        map1.put("id","1+1");
        map1.put("grid_composer",rolesProcessDTO.getComposer_1_1());
        map1.put("abr_resizer",rolesProcessDTO.getAbr_1_1());
        map1.put("encoder",rolesProcessDTO.getVideo_1_1());

        rolesProcessDTOlist.add(map1);

        if(rolesProcessDTO.getProcess_1_2()!=null){
            String[] Process_1_2 = rolesProcessDTO.getProcess_1_2().split(",");
            String[] Source_1_2 = rolesProcessDTO.getSource_1_2().split(",");
            String[] Resize_1_2 = rolesProcessDTO.getResize_1_2().split(",");
            for(int i=1; i<Process_1_2.length+1; i++){
                map2.put(text+i,Process_1_2[i-1]+","+Source_1_2[i-1]+","+Resize_1_2[i-1]);
            }
            map2.put("group_id",rolesDTO.getId());
            map2.put("preset_group_id",rolesDTO.getPreset_group_id_1());
            map2.put("id","1+2");
            map2.put("grid_composer",rolesProcessDTO.getComposer_1_2());
            map2.put("abr_resizer",rolesProcessDTO.getAbr_1_2());
            map2.put("encoder",rolesProcessDTO.getVideo_1_2());

            rolesProcessDTOlist.add(map2);
        }

        if(rolesProcessDTO.getProcess_1_3()!=null) {
            String[] Process_1_3 = rolesProcessDTO.getProcess_1_3().split(",");
            String[] Source_1_3 = rolesProcessDTO.getSource_1_3().split(",");
            String[] Resize_1_3 = rolesProcessDTO.getResize_1_3().split(",");
            for (int i = 1; i < Process_1_3.length + 1; i++) {
                map3.put(text+i, Process_1_3[i-1] + "," + Source_1_3[i-1] + "," + Resize_1_3[i-1]);
            }
            map3.put("group_id",rolesDTO.getId());
            map3.put("preset_group_id", rolesDTO.getPreset_group_id_1());
            map3.put("id", "1+3");
            map3.put("grid_composer", rolesProcessDTO.getComposer_1_3());
            map3.put("abr_resizer", rolesProcessDTO.getAbr_1_3());
            map3.put("encoder", rolesProcessDTO.getVideo_1_3());

            rolesProcessDTOlist.add(map3);
        }

        String[] Process2_1_1 = rolesProcessDTO.getProcess2_1_1().split(",");
        String[] Source2_1_1 = rolesProcessDTO.getSource2_1_1().split(",");
        String[] Resize2_1_1 = rolesProcessDTO.getResize2_1_1().split(",");
        for(int i=1; i<Process2_1_1.length+1; i++){
            map4.put(text+i,Process2_1_1[i-1]+","+Source2_1_1[i-1]+","+Resize2_1_1[i-1]);
        }
        map4.put("group_id",rolesDTO.getId());
        map4.put("preset_group_id",rolesDTO.getPreset_group_id_2());
        map4.put("id","thumbnail");
        map4.put("grid_composer",rolesProcessDTO.getComposer2_1_1());
        map4.put("abr_resizer",rolesProcessDTO.getAbr2_1_1());
        map4.put("encoder",rolesProcessDTO.getVideo2_1_1());

        rolesProcessDTOlist.add(map4);

        rolesDAO.insertRoles(rolesDTO); // TRANS_ROLE_INFO 테이블 insert
        insertPresetInfo(rolesProcessDTOlist); // MULTIVIEW_PRESET_INFO 테이블 insert
        updateSelectedByData(); //Roles 에서 사용된 설정값 (selected) 변경로직

        if(query.equals("update")) {
            return "update";
        } else if (query.equals("insert")) {
            return "insert";
        }
        return "fail";
    }
    public void insertPresetInfo(List<HashMap<String,String>> listPresetInfo){
        rolesDAO.insertPresetInfo(listPresetInfo);
    }
    public void updateSelectedByData() {
        rolesDAO.updateSelectedByInput();
        rolesDAO.updateSelectedByResize();
        rolesDAO.updateSelectedByComposer();
        rolesDAO.updateSelectedByVideo();
        rolesDAO.updateSelectedByPackager();
        }

    public void deleteRolesId(String id){
        rolesDAO.deleteRolesId(id);
        updateSelectedByData();
    };
    public void deletePresetId(String group_id){
        rolesDAO.deletePresetId(group_id);
    };
}

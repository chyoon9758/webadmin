package com.web.admin.service.api;

import com.web.admin.DAO.transcodingStandby.TranscodingStandbyDAO;
import com.web.admin.DTO.DeviceDTO;
import com.web.admin.DTO.MultiviewPresetInfoDTO;
import com.web.admin.DTO.PackagerDTO;
import com.web.admin.DTO.TransRoleInfoDTO;
import com.web.admin.DTO.api.transcodingStandby.TranscodingStandbyAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.UplusIdolliveConfigAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList.PackagerAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList.PipelineTemplateListAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.TransConfigsAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.gridComposerList.GridComposerListAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.gridComposerList.GridComposerListSplitAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.gridComposerList.GridMatrixAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.ManifestGeneratorAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.PackagerListAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.PublishingInfoAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.segmenter.DrmAPI;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.transConfigs.packagerList.segmenter.SegmenterAPI;
import com.web.admin.DAO.*;
import com.web.admin.DTO.api.transcodingStandby.uplusIdolliveConfig.pipelineTemplateList.multiviewPresetList.*;
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
public class TransCodingStandbyService {
    private final TranscodingStandbyDAO transcodingStandbyDAO;
    private final ChannelInputDAO channelInputDAO;
    private final ResizeDAO resizeDAO;
    private final PackagerDAO packagerDAO;
    private final ComposerDAO composerDAO;
    private final DeviceDAO deviceDAO;
    private final PresetVideoDAO presetVideoDAO;

    public List<EncoderAPI> getAllEncoder(){
        return transcodingStandbyDAO.getAllEncoder();
    }

    public DeviceDTO getDevice(){
        return deviceDAO.getDevice();
    };

    public TranscodingStandbyAPI getTranscodingStandbyAPI(String id){
        TranscodingStandbyAPI transcodingStandbyAPI = new TranscodingStandbyAPI();
        UplusIdolliveConfigAPI uplusIdolliveConfigAPI = new UplusIdolliveConfigAPI();
        uplusIdolliveConfigAPI.setTrans_configs(getTransConfigsAPI(id));
        uplusIdolliveConfigAPI.setPipeline_template_list(getPipelineTemplateListAPI(id));
        transcodingStandbyAPI.setUplus_idollive_config(uplusIdolliveConfigAPI);
        return transcodingStandbyAPI;
    }

    public TransConfigsAPI getTransConfigsAPI(String id){ //trans_configs 부분
        TransConfigsAPI transConfigsAPI = new TransConfigsAPI();
        TransRoleInfoDTO device = transcodingStandbyDAO.getTransRoleInfo(id); //해당 device
        //input_list 저장
        transConfigsAPI.setInput_list(channelInputDAO.getInputListAPI(device.getInput_group_id()));

        //resizer_list 저장
        transConfigsAPI.setResizer_list(resizeDAO.getResizerAPI(device.getResize_group_id()));

        //grid_composer_list 저장
        List<GridComposerListSplitAPI> gridComposerListSplitAPIList = composerDAO.getGridComposerListSplitAPI(device.getGrid_composer_group_id());
        List<GridComposerListAPI> gridComposerListAPIList = new ArrayList<GridComposerListAPI>();

        for(int i=0; i<gridComposerListSplitAPIList.size(); i++){
            GridComposerListAPI gridComposerListAPI = new GridComposerListAPI();
            gridComposerListAPI.setId(gridComposerListSplitAPIList.get(i).getId());
            gridComposerListAPI.setWidth(gridComposerListSplitAPIList.get(i).getWidth());
            gridComposerListAPI.setHeight(gridComposerListSplitAPIList.get(i).getHeight());

            List<GridMatrixAPI> gridMatrixAPIList = new ArrayList<GridMatrixAPI>();

            if(gridComposerListSplitAPIList.get(i).getGrid_matrix_0()!=null){
                GridMatrixAPI gridMatrixAPI0 = new GridMatrixAPI();
                String[] matrix0 = gridComposerListSplitAPIList.get(i).getGrid_matrix_0().split("/");
                gridMatrixAPI0.setLeft(matrix0[0]);
                gridMatrixAPI0.setTop(matrix0[1]);
                gridMatrixAPI0.setRight(matrix0[2]);
                gridMatrixAPI0.setBottom(matrix0[3]);
                gridMatrixAPIList.add(gridMatrixAPI0);

                if(gridComposerListSplitAPIList.get(i).getGrid_matrix_1()!=null){
                    GridMatrixAPI gridMatrixAPI1 = new GridMatrixAPI();
                    String[] matrix1 = gridComposerListSplitAPIList.get(i).getGrid_matrix_1().split("/");
                    gridMatrixAPI1.setLeft(matrix1[0]);
                    gridMatrixAPI1.setTop(matrix1[1]);
                    gridMatrixAPI1.setRight(matrix1[2]);
                    gridMatrixAPI1.setBottom(matrix1[3]);
                    gridMatrixAPIList.add(gridMatrixAPI1);

                    if(gridComposerListSplitAPIList.get(i).getGrid_matrix_2()!=null){
                        GridMatrixAPI gridMatrixAPI2 = new GridMatrixAPI();
                        String[] matrix2 = gridComposerListSplitAPIList.get(i).getGrid_matrix_2().split("/");
                        gridMatrixAPI2.setLeft(matrix2[0]);
                        gridMatrixAPI2.setTop(matrix2[1]);
                        gridMatrixAPI2.setRight(matrix2[2]);
                        gridMatrixAPI2.setBottom(matrix2[3]);
                        gridMatrixAPIList.add(gridMatrixAPI2);

                        if(gridComposerListSplitAPIList.get(i).getGrid_matrix_3()!=null){
                            GridMatrixAPI gridMatrixAPI3 = new GridMatrixAPI();
                            String[] matrix3 = gridComposerListSplitAPIList.get(i).getGrid_matrix_3().split("/");
                            gridMatrixAPI3.setLeft(matrix3[0]);
                            gridMatrixAPI3.setTop(matrix3[1]);
                            gridMatrixAPI3.setRight(matrix3[2]);
                            gridMatrixAPI3.setBottom(matrix3[3]);
                            gridMatrixAPIList.add(gridMatrixAPI3);

                            if(gridComposerListSplitAPIList.get(i).getGrid_matrix_4()!=null){
                                GridMatrixAPI gridMatrixAPI4 = new GridMatrixAPI();
                                String[] matrix4 = gridComposerListSplitAPIList.get(i).getGrid_matrix_4().split("/");
                                gridMatrixAPI4.setLeft(matrix4[0]);
                                gridMatrixAPI4.setTop(matrix4[1]);
                                gridMatrixAPI4.setRight(matrix4[2]);
                                gridMatrixAPI4.setBottom(matrix4[3]);
                                gridMatrixAPIList.add(gridMatrixAPI4);

                                if(gridComposerListSplitAPIList.get(i).getGrid_matrix_5()!=null){
                                    GridMatrixAPI gridMatrixAPI5 = new GridMatrixAPI();
                                    String[] matrix5 = gridComposerListSplitAPIList.get(i).getGrid_matrix_5().split("/");
                                    gridMatrixAPI5.setLeft(matrix5[0]);
                                    gridMatrixAPI5.setTop(matrix5[1]);
                                    gridMatrixAPI5.setRight(matrix5[2]);
                                    gridMatrixAPI5.setBottom(matrix5[3]);
                                    gridMatrixAPIList.add(gridMatrixAPI5);

                                    if(gridComposerListSplitAPIList.get(i).getGrid_matrix_6()!=null){
                                        GridMatrixAPI gridMatrixAPI6 = new GridMatrixAPI();
                                        String[] matrix6 = gridComposerListSplitAPIList.get(i).getGrid_matrix_6().split("/");
                                        gridMatrixAPI6.setLeft(matrix6[0]);
                                        gridMatrixAPI6.setTop(matrix6[1]);
                                        gridMatrixAPI6.setRight(matrix6[2]);
                                        gridMatrixAPI6.setBottom(matrix6[3]);
                                        gridMatrixAPIList.add(gridMatrixAPI6);

                                        if(gridComposerListSplitAPIList.get(i).getGrid_matrix_7()!=null){
                                            GridMatrixAPI gridMatrixAPI7 = new GridMatrixAPI();
                                            String[] matrix7 = gridComposerListSplitAPIList.get(i).getGrid_matrix_7().split("/");
                                            gridMatrixAPI7.setLeft(matrix7[0]);
                                            gridMatrixAPI7.setTop(matrix7[1]);
                                            gridMatrixAPI7.setRight(matrix7[2]);
                                            gridMatrixAPI7.setBottom(matrix7[3]);
                                            gridMatrixAPIList.add(gridMatrixAPI7);

                                            if(gridComposerListSplitAPIList.get(i).getGrid_matrix_8()!=null){
                                                GridMatrixAPI gridMatrixAPI8 = new GridMatrixAPI();
                                                String[] matrix8 = gridComposerListSplitAPIList.get(i).getGrid_matrix_8().split("/");
                                                gridMatrixAPI8.setLeft(matrix8[0]);
                                                gridMatrixAPI8.setTop(matrix8[1]);
                                                gridMatrixAPI8.setRight(matrix8[2]);
                                                gridMatrixAPI8.setBottom(matrix8[3]);
                                                gridMatrixAPIList.add(gridMatrixAPI8);

                                                if(gridComposerListSplitAPIList.get(i).getGrid_matrix_9()!=null){
                                                    GridMatrixAPI gridMatrixAPI9 = new GridMatrixAPI();
                                                    String[] matrix9 = gridComposerListSplitAPIList.get(i).getGrid_matrix_9().split("/");
                                                    gridMatrixAPI9.setLeft(matrix9[0]);
                                                    gridMatrixAPI9.setTop(matrix9[1]);
                                                    gridMatrixAPI9.setRight(matrix9[2]);
                                                    gridMatrixAPI9.setBottom(matrix9[3]);
                                                    gridMatrixAPIList.add(gridMatrixAPI9);

                                                    if(gridComposerListSplitAPIList.get(i).getGrid_matrix_10()!=null){
                                                        GridMatrixAPI gridMatrixAPI10 = new GridMatrixAPI();
                                                        String[] matrix10 = gridComposerListSplitAPIList.get(i).getGrid_matrix_10().split("/");
                                                        gridMatrixAPI10.setLeft(matrix10[0]);
                                                        gridMatrixAPI10.setTop(matrix10[1]);
                                                        gridMatrixAPI10.setRight(matrix10[2]);
                                                        gridMatrixAPI10.setBottom(matrix10[3]);
                                                        gridMatrixAPIList.add(gridMatrixAPI10);
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
            gridComposerListAPI.setGrid_matrix(gridMatrixAPIList);
            gridComposerListAPIList.add(gridComposerListAPI);
        }
        transConfigsAPI.setGrid_composer_list(gridComposerListAPIList);

        // packager_list 저장
        PackagerDTO packagerDTO1 = packagerDAO.getPackagerByGroupId(device.getPackager_id_1());
        PackagerDTO packagerDTO2 = packagerDAO.getPackagerByGroupId(device.getPackager_id_2());
        DrmAPI drmAPI1 = packagerDAO.getDrm(packagerDTO1);
        DrmAPI drmAPI2 = packagerDAO.getDrm(packagerDTO2);
        SegmenterAPI segmenterAPI1 = packagerDAO.getSegmenter(packagerDTO1);
        SegmenterAPI segmenterAPI2 = packagerDAO.getSegmenter(packagerDTO2);
        segmenterAPI1.setDrm(drmAPI1);
        segmenterAPI2.setDrm(drmAPI2);
        ManifestGeneratorAPI manifestGeneratorAPI1 = packagerDAO.getManifestGenerator(packagerDTO1);
        ManifestGeneratorAPI manifestGeneratorAPI2 = packagerDAO.getManifestGenerator(packagerDTO2);
        PublishingInfoAPI publishingInfoAPI1 = packagerDAO.getPublishingInfo(packagerDTO1);
        PublishingInfoAPI publishingInfoAPI2 = packagerDAO.getPublishingInfo(packagerDTO2);
        PackagerListAPI packagerListAPI1 = new PackagerListAPI();
        PackagerListAPI packagerListAPI2 = new PackagerListAPI();
        packagerListAPI1.setId(packagerDTO1.getId());
        packagerListAPI1.setSegmenter(segmenterAPI1);
        packagerListAPI1.setManifest_generator(manifestGeneratorAPI1);
        packagerListAPI1.setPublishing_info(publishingInfoAPI1);
        packagerListAPI2.setId(packagerDTO2.getId());
        packagerListAPI2.setSegmenter(segmenterAPI2);
        packagerListAPI2.setManifest_generator(manifestGeneratorAPI2);
        packagerListAPI2.setPublishing_info(publishingInfoAPI2);
        List<PackagerListAPI> packagerListAPIList = new ArrayList<PackagerListAPI>();
        packagerListAPIList.add(packagerListAPI1);
        packagerListAPIList.add(packagerListAPI2);
        transConfigsAPI.setPackager_list(packagerListAPIList);

        //abr_resizer_list 저장
        transConfigsAPI.setAbr_resizer_list(resizeDAO.getAbrResizerAPI(device.getAbr_resizer_group_id()));

        //encoder_list 저장
        transConfigsAPI.setEncoder_list(presetVideoDAO.getEncoderListAPI(device.getEncoder_group_id()));


        return transConfigsAPI;
    }

    public List<PipelineTemplateListAPI> getPipelineTemplateListAPI(String group_id){ //pipeline_template_list 부분
        TransRoleInfoDTO device = transcodingStandbyDAO.getTransRoleInfo(group_id); // 선택 Device(group_id)
        String Preset_group_id_1 = device.getPreset_group_id_1(); //0
        String Preset_group_id_2 = device.getPreset_group_id_2(); //1

        //multiview_preset_list1 저장
        List<MultiviewPresetListAPI> multiviewPresetListAPIList1 = new ArrayList<MultiviewPresetListAPI>();
        List<MultiviewPresetInfoDTO> multiviewPresetInfoDTOList1 = transcodingStandbyDAO.getMultiviewPresetInfo(group_id, Preset_group_id_1);

        for(int i=0; i<multiviewPresetInfoDTOList1.size(); i++){
            if(!multiviewPresetInfoDTOList1.get(i).getId().equals("thumbnail")){
                List<PreGridProcessAPI> preGridProcessAPIList = new ArrayList<PreGridProcessAPI>();
                MultiviewPresetListAPI multiviewPresetListAPI = new MultiviewPresetListAPI();
                if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_1()!=null){
                    PreGridProcessAPI preGridProcessAPI1 = new PreGridProcessAPI();
                    String[] strPreGridProcess1 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_1().split(",", -1);
                    preGridProcessAPI1.setSource_id(strPreGridProcess1[1]);
                    preGridProcessAPI1.setResizer_id(strPreGridProcess1[2]);
                    preGridProcessAPIList.add(preGridProcessAPI1);

                    if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_2()!=null){
                        PreGridProcessAPI preGridProcessAPI2 = new PreGridProcessAPI();
                        String[] strPreGridProcess2 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_2().split(",", -1);
                        preGridProcessAPI2.setSource_id(strPreGridProcess2[1]);
                        preGridProcessAPI2.setResizer_id(strPreGridProcess2[2]);
                        preGridProcessAPIList.add(preGridProcessAPI2);

                        if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_3()!=null){
                            PreGridProcessAPI preGridProcessAPI3 = new PreGridProcessAPI();
                            String[] strPreGridProcess3 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_3().split(",", -1);
                            preGridProcessAPI3.setSource_id(strPreGridProcess3[1]);
                            preGridProcessAPI3.setResizer_id(strPreGridProcess3[2]);
                            preGridProcessAPIList.add(preGridProcessAPI3);

                            if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_4()!=null){
                                PreGridProcessAPI preGridProcessAPI4 = new PreGridProcessAPI();
                                String[] strPreGridProcess4 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_4().split(",", -1);
                                preGridProcessAPI4.setSource_id(strPreGridProcess4[1]);
                                preGridProcessAPI4.setResizer_id(strPreGridProcess4[2]);
                                preGridProcessAPIList.add(preGridProcessAPI4);

                                if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_5()!=null){
                                    PreGridProcessAPI preGridProcessAPI5 = new PreGridProcessAPI();
                                    String[] strPreGridProcess5 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_5().split(",", -1);
                                    preGridProcessAPI5.setSource_id(strPreGridProcess5[1]);
                                    preGridProcessAPI5.setResizer_id(strPreGridProcess5[2]);
                                    preGridProcessAPIList.add(preGridProcessAPI5);

                                    if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_6()!=null){
                                        PreGridProcessAPI preGridProcessAPI6 = new PreGridProcessAPI();
                                        String[] strPreGridProcess6 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_6().split(",", -1);
                                        preGridProcessAPI6.setSource_id(strPreGridProcess6[1]);
                                        preGridProcessAPI6.setResizer_id(strPreGridProcess6[2]);
                                        preGridProcessAPIList.add(preGridProcessAPI6);

                                        if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_7()!=null){
                                            PreGridProcessAPI preGridProcessAPI7 = new PreGridProcessAPI();
                                            String[] strPreGridProcess7 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_7().split(",", -1);
                                            preGridProcessAPI7.setSource_id(strPreGridProcess7[1]);
                                            preGridProcessAPI7.setResizer_id(strPreGridProcess7[2]);
                                            preGridProcessAPIList.add(preGridProcessAPI7);

                                            if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_8()!=null){
                                                PreGridProcessAPI preGridProcessAPI8 = new PreGridProcessAPI();
                                                String[] strPreGridProcess8 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_8().split(",", -1);
                                                preGridProcessAPI8.setSource_id(strPreGridProcess8[1]);
                                                preGridProcessAPI8.setResizer_id(strPreGridProcess8[2]);
                                                preGridProcessAPIList.add(preGridProcessAPI8);

                                                if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_9()!=null){
                                                    PreGridProcessAPI preGridProcessAPI9 = new PreGridProcessAPI();
                                                    String[] strPreGridProcess9 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_9().split(",", -1);
                                                    preGridProcessAPI9.setSource_id(strPreGridProcess9[1]);
                                                    preGridProcessAPI9.setResizer_id(strPreGridProcess9[2]);
                                                    preGridProcessAPIList.add(preGridProcessAPI9);

                                                    if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_10()!=null){
                                                        PreGridProcessAPI preGridProcessAPI10 = new PreGridProcessAPI();
                                                        String[] strPreGridProcess10 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_10().split(",", -1);
                                                        preGridProcessAPI10.setSource_id(strPreGridProcess10[1]);
                                                        preGridProcessAPI10.setResizer_id(strPreGridProcess10[2]);
                                                        preGridProcessAPIList.add(preGridProcessAPI10);

                                                        if(multiviewPresetInfoDTOList1.get(i).getPre_grid_process_11()!=null){
                                                            PreGridProcessAPI preGridProcessAPI11 = new PreGridProcessAPI();
                                                            String[] strPreGridProcess11 = multiviewPresetInfoDTOList1.get(i).getPre_grid_process_11().split(",", -1);
                                                            preGridProcessAPI11.setSource_id(strPreGridProcess11[1]);
                                                            preGridProcessAPI11.setResizer_id(strPreGridProcess11[2]);
                                                            preGridProcessAPIList.add(preGridProcessAPI11);
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
                multiviewPresetListAPI.setId(multiviewPresetInfoDTOList1.get(i).getId());
                multiviewPresetListAPI.setPre_grid_process(preGridProcessAPIList);

                GridComposerAPI gridComposerAPI = new GridComposerAPI();
                gridComposerAPI.setConfig_id(multiviewPresetInfoDTOList1.get(i).getGrid_composer());
                multiviewPresetListAPI.setGrid_composer(gridComposerAPI);

                List<EncoderAPI> encoderAPIList = new ArrayList<EncoderAPI>();
                String[] strEncoderAPI = multiviewPresetInfoDTOList1.get(i).getEncoder().split(",");
                for(int j=0; j<strEncoderAPI.length; j++){
                    EncoderAPI encoderAPI = new EncoderAPI();
                    encoderAPI.setConfig_id(strEncoderAPI[j]);
                    encoderAPIList.add(encoderAPI);
                }
                multiviewPresetListAPI.setEncoder(encoderAPIList);

                List<AbrResizerAPI> abrResizerAPIList = new ArrayList<AbrResizerAPI>();
                String[] strAbrResizerAPI = multiviewPresetInfoDTOList1.get(i).getAbr_resizer().split(",");
                for(int j=0; j<strAbrResizerAPI.length; j++){
                    AbrResizerAPI abrResizerAPI = new AbrResizerAPI();
                    abrResizerAPI.setConfig_id(strAbrResizerAPI[j]);
                    abrResizerAPIList.add(abrResizerAPI);
                }
                multiviewPresetListAPI.setAbr_resizer(abrResizerAPIList);
                multiviewPresetListAPIList1.add(multiviewPresetListAPI);
            }
        }
        //packager1 저장
        PackagerAPI packagerAPI1 = new PackagerAPI();
        packagerAPI1.setConfig_id(Integer.parseInt(packagerDAO.getPackagerByGroupId(device.getPackager_id_1()).getId()));

        //multiview_preset_list2 저장
        List<MultiviewPresetListAPI> multiviewPresetListAPIList2 = new ArrayList<MultiviewPresetListAPI>();
        List<MultiviewPresetInfoDTO> multiviewPresetInfoDTOList2 = transcodingStandbyDAO.getMultiviewPresetInfo(group_id, Preset_group_id_2);
        for(int i=0; i<multiviewPresetInfoDTOList2.size(); i++){
            if(multiviewPresetInfoDTOList2.get(i).getId().equals("thumbnail")){
                List<PreGridProcessAPI> preGridProcessAPIList = new ArrayList<PreGridProcessAPI>();
                MultiviewPresetListAPI multiviewPresetListAPI = new MultiviewPresetListAPI();
                if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_1()!=null){
                    PreGridProcessAPI preGridProcessAPI1 = new PreGridProcessAPI();
                    String[] strPreGridProcess1 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_1().split(",", -1);
                    preGridProcessAPI1.setSource_id(strPreGridProcess1[1]);
                    preGridProcessAPI1.setResizer_id(strPreGridProcess1[2]);
                    preGridProcessAPIList.add(preGridProcessAPI1);

                    if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_2()!=null){
                        PreGridProcessAPI preGridProcessAPI2 = new PreGridProcessAPI();
                        String[] strPreGridProcess2 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_2().split(",", -1);
                        preGridProcessAPI2.setSource_id(strPreGridProcess2[1]);
                        preGridProcessAPI2.setResizer_id(strPreGridProcess2[2]);
                        preGridProcessAPIList.add(preGridProcessAPI2);

                        if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_3()!=null){
                            PreGridProcessAPI preGridProcessAPI3 = new PreGridProcessAPI();
                            String[] strPreGridProcess3 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_3().split(",", -1);
                            preGridProcessAPI3.setSource_id(strPreGridProcess3[1]);
                            preGridProcessAPI3.setResizer_id(strPreGridProcess3[2]);
                            preGridProcessAPIList.add(preGridProcessAPI3);

                            if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_4()!=null){
                                PreGridProcessAPI preGridProcessAPI4 = new PreGridProcessAPI();
                                String[] strPreGridProcess4 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_4().split(",", -1);
                                preGridProcessAPI4.setSource_id(strPreGridProcess4[1]);
                                preGridProcessAPI4.setResizer_id(strPreGridProcess4[2]);
                                preGridProcessAPIList.add(preGridProcessAPI4);

                                if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_5()!=null){
                                    PreGridProcessAPI preGridProcessAPI5 = new PreGridProcessAPI();
                                    String[] strPreGridProcess5 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_5().split(",", -1);
                                    preGridProcessAPI5.setSource_id(strPreGridProcess5[1]);
                                    preGridProcessAPI5.setResizer_id(strPreGridProcess5[2]);
                                    preGridProcessAPIList.add(preGridProcessAPI5);

                                    if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_6()!=null){
                                        PreGridProcessAPI preGridProcessAPI6 = new PreGridProcessAPI();
                                        String[] strPreGridProcess6 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_6().split(",", -1);
                                        preGridProcessAPI6.setSource_id(strPreGridProcess6[1]);
                                        preGridProcessAPI6.setResizer_id(strPreGridProcess6[2]);
                                        preGridProcessAPIList.add(preGridProcessAPI6);

                                        if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_7()!=null){
                                            PreGridProcessAPI preGridProcessAPI7 = new PreGridProcessAPI();
                                            String[] strPreGridProcess7 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_7().split(",", -1);
                                            preGridProcessAPI7.setSource_id(strPreGridProcess7[1]);
                                            preGridProcessAPI7.setResizer_id(strPreGridProcess7[2]);
                                            preGridProcessAPIList.add(preGridProcessAPI7);

                                            if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_8()!=null){
                                                PreGridProcessAPI preGridProcessAPI8 = new PreGridProcessAPI();
                                                String[] strPreGridProcess8 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_8().split(",", -1);
                                                preGridProcessAPI8.setSource_id(strPreGridProcess8[1]);
                                                preGridProcessAPI8.setResizer_id(strPreGridProcess8[2]);
                                                preGridProcessAPIList.add(preGridProcessAPI8);

                                                if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_9()!=null){
                                                    PreGridProcessAPI preGridProcessAPI9 = new PreGridProcessAPI();
                                                    String[] strPreGridProcess9 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_9().split(",", -1);
                                                    preGridProcessAPI9.setSource_id(strPreGridProcess9[1]);
                                                    preGridProcessAPI9.setResizer_id(strPreGridProcess9[2]);
                                                    preGridProcessAPIList.add(preGridProcessAPI9);

                                                    if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_10()!=null){
                                                        PreGridProcessAPI preGridProcessAPI10 = new PreGridProcessAPI();
                                                        String[] strPreGridProcess10 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_10().split(",", -1);
                                                        preGridProcessAPI10.setSource_id(strPreGridProcess10[1]);
                                                        preGridProcessAPI10.setResizer_id(strPreGridProcess10[2]);
                                                        preGridProcessAPIList.add(preGridProcessAPI10);

                                                        if(multiviewPresetInfoDTOList2.get(i).getPre_grid_process_11()!=null){
                                                            PreGridProcessAPI preGridProcessAPI11 = new PreGridProcessAPI();
                                                            String[] strPreGridProcess11 = multiviewPresetInfoDTOList2.get(i).getPre_grid_process_11().split(",", -1);
                                                            preGridProcessAPI11.setSource_id(strPreGridProcess11[1]);
                                                            preGridProcessAPI11.setResizer_id(strPreGridProcess11[2]);
                                                            preGridProcessAPIList.add(preGridProcessAPI11);
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
                multiviewPresetListAPI.setId(multiviewPresetInfoDTOList2.get(i).getId());
                multiviewPresetListAPI.setPre_grid_process(preGridProcessAPIList);

                GridComposerAPI gridComposerAPI = new GridComposerAPI();
                gridComposerAPI.setConfig_id(multiviewPresetInfoDTOList2.get(i).getGrid_composer());
                multiviewPresetListAPI.setGrid_composer(gridComposerAPI);

                List<EncoderAPI> encoderAPIList = new ArrayList<EncoderAPI>();
                String[] strEncoderAPI = multiviewPresetInfoDTOList2.get(i).getEncoder().split(",");
                for(int j=0; j<strEncoderAPI.length; j++){
                    EncoderAPI encoderAPI = new EncoderAPI();
                    encoderAPI.setConfig_id(strEncoderAPI[j]);
                    encoderAPIList.add(encoderAPI);
                }
                multiviewPresetListAPI.setEncoder(encoderAPIList);

                List<AbrResizerAPI> abrResizerAPIList = new ArrayList<AbrResizerAPI>();
                String[] strAbrResizerAPI = multiviewPresetInfoDTOList2.get(i).getAbr_resizer().split(",");
                for(int j=0; j<strAbrResizerAPI.length; j++){
                    AbrResizerAPI abrResizerAPI = new AbrResizerAPI();
                    abrResizerAPI.setConfig_id(strAbrResizerAPI[j]);
                    abrResizerAPIList.add(abrResizerAPI);
                }
                multiviewPresetListAPI.setAbr_resizer(abrResizerAPIList);
                multiviewPresetListAPIList2.add(multiviewPresetListAPI);
            }
        }

        //packager2 저장
        PackagerAPI packagerAPI2 = new PackagerAPI();
        packagerAPI2.setConfig_id(Integer.parseInt(packagerDAO.getPackagerByGroupId(device.getPackager_id_2()).getId()));

        //pipeline_template_list 저장
        List<PipelineTemplateListAPI> pipelineTemplateListAPIList = new ArrayList<PipelineTemplateListAPI>();
        PipelineTemplateListAPI pipelineTemplateListAPI1 = new PipelineTemplateListAPI();
        pipelineTemplateListAPI1.setMultiview_preset_list(multiviewPresetListAPIList1);
        pipelineTemplateListAPI1.setPackager(packagerAPI1);
        PipelineTemplateListAPI pipelineTemplateListAPI2 = new PipelineTemplateListAPI();
        pipelineTemplateListAPI2.setMultiview_preset_list(multiviewPresetListAPIList2);
        pipelineTemplateListAPI2.setPackager(packagerAPI2);
        pipelineTemplateListAPIList.add(pipelineTemplateListAPI1);
        pipelineTemplateListAPIList.add(pipelineTemplateListAPI2);

        return pipelineTemplateListAPIList;
    }
}

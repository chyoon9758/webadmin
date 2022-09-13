package com.web.admin.service.api;

import com.web.admin.DAO.DeviceDAO;
import com.web.admin.DTO.DeviceDTO;
import com.web.admin.DTO.api.getStatus.idolLive.GetStatusAPI;
import com.web.admin.DTO.api.getStatus.idolLive.IdolLiveAPI;
import com.web.admin.DTO.api.getStatus.idolLive.inputStatistics.InputStatisticsAPI;
import com.web.admin.DTO.api.getStatus.idolLive.inputStatistics.ThumbnailInfoAPI;
import com.web.admin.DTO.api.getStatus.idolLive.outputInfo.MediaInfoListAPI;
import com.web.admin.DTO.api.getStatus.idolLive.outputInfo.OutputInfoAPI;
import com.web.admin.DTO.api.getStatus.idolLive.outputInfo.PublishingPointInfoAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class GettingStatusService {
    private final DeviceDAO deviceDAO;

    public GetStatusAPI getStatusAPI() throws IOException {
        RestTemplate restTemplate = new RestTemplateBuilder().setConnectTimeout(Duration.ofSeconds(1)).setReadTimeout(Duration.ofSeconds(1)).build();
        DeviceDTO device = deviceDAO.getDevice();
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject = new JSONObject(restTemplate.getForObject(device.getIp() + "/transcode/status", String.class));
        } catch (Exception e) {
            log.info(e+"");
        }
        log.info("Status : " + jsonObject);
        GetStatusAPI getStatusAPI = new GetStatusAPI();
        IdolLiveAPI idolLiveAPI = new IdolLiveAPI();
        List<InputStatisticsAPI> inputStatisticsAPIList = new ArrayList<InputStatisticsAPI>();
        List<OutputInfoAPI> outputInfoAPIList = new ArrayList<OutputInfoAPI>();
        BufferedImage image = null; // 이미지 담을 객체


        if(jsonObject.has("idolLive")) {
            JSONObject idolLive = jsonObject.getJSONObject("idolLive");
            if(idolLive.has("status") && !idolLive.getString("status").isEmpty())
                idolLiveAPI.setStatus(idolLive.getString("status"));
            if(idolLive.has("errorcode") && !idolLive.getString("errorcode").isEmpty())
                idolLiveAPI.setErrorCode(idolLive.getString("errorcode"));
            if(idolLive.has("errorString") && !idolLive.getString("errorString").isEmpty())
                idolLiveAPI.setErrorString(idolLive.getString("errorString"));
            if(idolLive.has("elapsedTime") && !idolLive.getString("elapsedTime").isEmpty())
                idolLiveAPI.setElapsedTime(idolLive.getString("elapsedTime"));
            if(idolLive.has("inputStatistics") && !idolLive.isNull("inputStatistics")) {
                JSONArray arr_inputStatistics = idolLive.getJSONArray("inputStatistics");
                for (int i = 0; i < arr_inputStatistics.length(); i++) {
                    JSONObject inputStatistics = arr_inputStatistics.getJSONObject(i);
                    InputStatisticsAPI inputStatisticsAPI = new InputStatisticsAPI();
                    if (inputStatistics.has("id") && !inputStatistics.getString("id").isEmpty())
                        inputStatisticsAPI.setId(inputStatistics.getString("id"));
                    if (inputStatistics.has("videoInfo") && !inputStatistics.getString("videoInfo").isEmpty())
                        inputStatisticsAPI.setVideoInfo(inputStatistics.getString("videoInfo"));
                    if (inputStatistics.has("audioRateLevel") && !inputStatistics.getString("audioRateLevel").isEmpty())
                        inputStatisticsAPI.setAudioRateLevel(inputStatistics.getString("audioRateLevel"));
                    if (inputStatistics.has("audioMaxlevel") && !inputStatistics.getString("audioMaxlevel").isEmpty())
                        inputStatisticsAPI.setAudioMaxlevel(inputStatistics.getString("audioMaxlevel"));
                    if (inputStatistics.has("aspectRatio") && !inputStatistics.getString("aspectRatio").isEmpty())
                        inputStatisticsAPI.setAspectRatio(inputStatistics.getString("aspectRatio"));
                    if (inputStatistics.has("frames") && !inputStatistics.getString("frames").isEmpty())
                        inputStatisticsAPI.setFrames(inputStatistics.getString("frames"));
                    if (inputStatistics.has("uptime") && !inputStatistics.getString("uptime").isEmpty())
                        inputStatisticsAPI.setUptime(inputStatistics.getString("uptime"));
                    ThumbnailInfoAPI thumbnailInfoAPI = new ThumbnailInfoAPI();
                    JSONObject thumbnailInfo = inputStatistics.getJSONObject("thumbnailInfo");
                    if (thumbnailInfo.has("videoUrl") && !thumbnailInfo.getString("videoUrl").isEmpty()){
                        String videoUrl = thumbnailInfo.getString("videoUrl");
                        URL imageUrl = new URL(videoUrl);
                        try{
                            image = ImageIO.read(imageUrl);
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            ImageIO.write(image, "jpg", baos);

                            String base64String = DatatypeConverter.printBase64Binary(baos.toByteArray());
                            String dataUrlString = "data:image/jpg;base64," + base64String;
                            thumbnailInfoAPI.setVideoUrl(dataUrlString);

                            baos.flush();
                            baos.close();
                        }catch(Exception e){
                            if(inputStatisticsAPI.getId().equals("PGM"))
                                thumbnailInfoAPI.setVideoUrl("/img/default_304x174.jpg");
                            else
                                thumbnailInfoAPI.setVideoUrl("/img/default_174x304.jpg");
                            log.info(e.getMessage());
                        }

                    }
                    if (thumbnailInfo.has("audioUrl") && !thumbnailInfo.getString("audioUrl").isEmpty())
                        thumbnailInfoAPI.setAudioUrl(thumbnailInfo.getString("audioUrl"));
                    inputStatisticsAPI.setThumbnailInfo(thumbnailInfoAPI);
                    inputStatisticsAPIList.add(inputStatisticsAPI);
                }
            }
            if(idolLive.has("outputInfo") && !idolLive.isNull("outputInfo")) {
                JSONArray arr_outputInfo = idolLive.getJSONArray("outputInfo");
                for(int i=0; i<arr_outputInfo.length(); i++){
                    JSONObject outputInfo = arr_outputInfo.getJSONObject(i);
                    OutputInfoAPI outputInfoAPI = new OutputInfoAPI();
                    List<PublishingPointInfoAPI> publishingPointInfoAPIList = new ArrayList<PublishingPointInfoAPI>();
                    List<MediaInfoListAPI> mediaInfoListAPIList = new ArrayList<MediaInfoListAPI>();

                    JSONArray arr_publishingPointInfo = outputInfo.getJSONArray("publishingPointInfo");
                    for(int j=0; j<arr_publishingPointInfo.length(); j++){
                        JSONObject publishingPointInfo = arr_publishingPointInfo.getJSONObject(j);
                        PublishingPointInfoAPI publishingPointInfoAPI = new PublishingPointInfoAPI();
                        if(publishingPointInfo.has("type") && !publishingPointInfo.getString("type").isEmpty())
                            publishingPointInfoAPI.setType(publishingPointInfo.getString("type"));
                        if(publishingPointInfo.has("publishingPoint") && !publishingPointInfo.getString("publishingPoint").isEmpty())
                            publishingPointInfoAPI.setPublishingPoint(publishingPointInfo.getString("publishingPoint"));
                        publishingPointInfoAPIList.add(publishingPointInfoAPI);
                    }

                    JSONArray arr_mediaInfoList = outputInfo.getJSONArray("mediaInfoList");
                    for(int j=0; j<arr_mediaInfoList.length(); j++){
                        JSONObject mediaInfoList = arr_mediaInfoList.getJSONObject(j);
                        MediaInfoListAPI mediaInfoListAPI = new MediaInfoListAPI();
                        if(mediaInfoList.has("type") && !mediaInfoList.getString("type").isEmpty())
                            mediaInfoListAPI.setType(mediaInfoList.getString("type"));
                        if(mediaInfoList.has("presetCodec") && !mediaInfoList.getString("presetCodec").isEmpty())
                            mediaInfoListAPI.setPresetCodec(mediaInfoList.getString("presetCodec"));
                        if(mediaInfoList.has("presetWidth") && !mediaInfoList.getString("presetWidth").isEmpty())
                            mediaInfoListAPI.setPresetWidth(mediaInfoList.getString("presetWidth"));
                        if(mediaInfoList.has("presetHeight") && !mediaInfoList.getString("presetHeight").isEmpty())
                            mediaInfoListAPI.setPresetHeight(mediaInfoList.getString("presetHeight"));
                        if(mediaInfoList.has("presetFps") && !mediaInfoList.getString("presetFps").isEmpty())
                            mediaInfoListAPI.setPresetFps(mediaInfoList.getString("presetFps"));
                        if(mediaInfoList.has("presetRate") && !mediaInfoList.getString("presetRate").isEmpty())
                            mediaInfoListAPI.setPresetRate(mediaInfoList.getString("presetRate"));
                        if(mediaInfoList.has("presetSampleRate") && !mediaInfoList.getString("presetSampleRate").isEmpty())
                            mediaInfoListAPI.setPresetSampleRate(mediaInfoList.getString("presetSampleRate"));
                        if(mediaInfoList.has("presetChannel") && !mediaInfoList.getString("presetChannel").isEmpty())
                            mediaInfoListAPI.setPresetChannel(mediaInfoList.getString("presetChannel"));
                        if(mediaInfoList.has("rate") && !mediaInfoList.getString("rate").isEmpty())
                            mediaInfoListAPI.setRate(mediaInfoList.getString("rate"));
                        if(mediaInfoList.has("fps") && !mediaInfoList.getString("fps").isEmpty())
                            mediaInfoListAPI.setFps(mediaInfoList.getString("fps"));
                        if(mediaInfoList.has("name") && !mediaInfoList.getString("name").isEmpty())
                            mediaInfoListAPI.setName(mediaInfoList.getString("name"));
                        mediaInfoListAPIList.add(mediaInfoListAPI);
                    }
                    outputInfoAPI.setPublishingPointInfo(publishingPointInfoAPIList);
                    outputInfoAPI.setMediaInfoList(mediaInfoListAPIList);
                    outputInfoAPIList.add(outputInfoAPI);
                }
            }
            idolLiveAPI.setInputStatistics(inputStatisticsAPIList);
            idolLiveAPI.setOutputInfo(outputInfoAPIList);
            getStatusAPI.setIdolLive(idolLiveAPI);
        }
        return getStatusAPI;
    }

}

package com.web.admin.DTO.api.getStatus.idolLive;

import com.web.admin.DTO.api.getStatus.idolLive.inputStatistics.InputStatisticsAPI;
import com.web.admin.DTO.api.getStatus.idolLive.outputInfo.OutputInfoAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdolLiveAPI {
    private String status;
    private String errorCode;
    private String errorString;
    private String elapsedTime;
    private List<InputStatisticsAPI> inputStatistics;
    private List<OutputInfoAPI> outputInfo;
}

package com.web.admin.DTO.api.transcodingStartStop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdolLiveAPI {
    private String status;
    private int errorCode;
    private String errorString;
    private String elapsedTime;
}

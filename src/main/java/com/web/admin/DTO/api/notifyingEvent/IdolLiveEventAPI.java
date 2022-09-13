package com.web.admin.DTO.api.notifyingEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdolLiveEventAPI {
    private String status;
    private String messageLevel;
    private String message;
    private String errorCode;
    private String errorString;
    private String errorCode2;
    private String errorString2;
    private String elapsedTime;
    private String date;
}

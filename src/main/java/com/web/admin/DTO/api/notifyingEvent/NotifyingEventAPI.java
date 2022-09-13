package com.web.admin.DTO.api.notifyingEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotifyingEventAPI {
    private List<IdolLiveEventAPI> idolLiveEvent;
}

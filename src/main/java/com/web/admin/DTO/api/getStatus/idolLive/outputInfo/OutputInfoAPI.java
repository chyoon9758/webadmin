package com.web.admin.DTO.api.getStatus.idolLive.outputInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputInfoAPI {
    private List<PublishingPointInfoAPI> publishingPointInfo;
    private List<MediaInfoListAPI> mediaInfoList;
}

package com.web.admin.DTO.api.getNetTrafficInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SourceListAPI {
    private String src_addr;
    private String dst_addr;
    private int total_bitrate;
    private int menu;
    private String title;
    private List<TrakListAPI> trak_list;
}

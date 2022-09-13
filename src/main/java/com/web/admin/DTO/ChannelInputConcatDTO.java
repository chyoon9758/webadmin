package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChannelInputConcatDTO {
    private String group_id;
    private String id_concat;
    private int selected;

}

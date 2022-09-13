package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResizeConcatDTO {
    private String group_id;
    private String channels;//"width x height"
    private int selected;
}

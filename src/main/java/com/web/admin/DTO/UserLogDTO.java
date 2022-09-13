package com.web.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogDTO {

    private int idx;
    private String user;
    private String logString;
    private String date;

}

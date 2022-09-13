package com.web.admin.service;

import com.web.admin.DAO.UserLogDAO;
import com.web.admin.DTO.UserDTO;
import com.web.admin.DTO.UserLogDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserLogService {

    private final UserLogDAO userLogDAO;

    @Value("${user.logcount}")
    private int userlogcnt;

    public List<UserLogDTO> getListAll(int userlogcnt){return userLogDAO.getListAll(userlogcnt);}

    public void getUserLog(@AuthenticationPrincipal UserDTO userDTO, UserLogDTO userLogDTO){
        userLogDTO.setUser(userDTO.getId()); //로그인된 계정 DTO 객체에 저장
        insertUserLog(userLogDTO);
    }
    public void getUserLog2(String id, String logString){

        UserLogDTO userLogDTO = new UserLogDTO();
        userLogDTO.setUser(id); //로그인할 계정 DTO 객체에 저장
        userLogDTO.setLogString(logString); //로그인할 계정 DTO 객체에 저장
        insertUserLog(userLogDTO);
    }

    public void insertUserLog(UserLogDTO userLogDTO){
        userLogDAO.insertUserLog(userLogDTO);
        deleteUserLog(userlogcnt);
    }

    public void deleteUserLog(int userlogcnt){userLogDAO.deleteUserLog(userlogcnt);}

}

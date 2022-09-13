package com.web.admin.DAO;

import com.web.admin.DTO.UserLogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserLogDAO {

    List<UserLogDTO> getListAll(int userlogcnt);

    void insertUserLog(UserLogDTO userLogDTO);
    void deleteUserLog(int userlogcnt);
}

package com.web.admin.DAO;

import com.web.admin.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDAO {
    UserDTO getUserById(String id);
    void insertUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
}

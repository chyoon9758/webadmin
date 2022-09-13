package com.web.admin.service;

import com.web.admin.DAO.UserDAO;
import com.web.admin.DAO.UserLogDAO;
import com.web.admin.DTO.ChangePwDTO;
import com.web.admin.DTO.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;
    private final UserLogDAO userLogDAO;
    private final UserLogService userLogService;
    public UserDTO getUserById(String id){
        return userDAO.getUserById(id);
    }

    public String changePw(UserDTO userDTO, ChangePwDTO changePw){//비밀번호 변경
        if(new BCryptPasswordEncoder().matches(changePw.getOldPw(), getUserById(userDTO.getUsername()).getPassword())){//입력한 이전 비번이 맞으면
            if(changePw.getNewPw().equals(changePw.getConfirmPw())){//새로 입력한 비번이 같으면
                userDTO.setPassword(new BCryptPasswordEncoder().encode(changePw.getNewPw()));
                userDAO.updateUser(userDTO);
                return "succeed";
            }else{
                return "notEqualNewPw";
            }
        }else{
            return "notEqualOldPw";
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userDAO.getUserById(username);

        if(user==null){
            throw new UsernameNotFoundException(username);
        }

        return user;
    }

}

package com.web.admin.controller;

import com.web.admin.DTO.ChangePwDTO;
import com.web.admin.DTO.UserDTO;
import com.web.admin.DTO.UserLogDTO;
import com.web.admin.service.UserLogService;
import com.web.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final UserLogService userLogService;
    @GetMapping("")
    public String index(@AuthenticationPrincipal UserDTO userDTO){
        return "redirect:dashboard/monitor/main";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String errorMessage, Model model,@AuthenticationPrincipal UserDTO userDTO, UserLogDTO userLogDTO){
        model.addAttribute("errorMessage", errorMessage);

        return "login";
    }
    @GetMapping("/access-denied")
    public String access_denial(Model model, UserLogDTO userLogDTO, @AuthenticationPrincipal UserDTO userDTO){

        model.addAttribute("msg", "접근 권한이 없습니다.");
        model.addAttribute("url", "/dashboard/monitor/main");

        userLogDTO.setLogString("Configure 접근 제한");
        userLogService.getUserLog(userDTO,userLogDTO);

        return "alert";
    }


    @PostMapping("/changePw")
    @ResponseBody
    public String changePw(@AuthenticationPrincipal UserDTO userDTO, ChangePwDTO changePw, UserLogDTO userLogDTO) {
        if(userService.changePw(userDTO, changePw).equals("succeed")){
            userLogDTO.setLogString(userDTO.getId()+" 패스워드 변경");
            userLogService.getUserLog(userDTO,userLogDTO);
            return "<script>alert(\"비밀번호가 변경되었습니다. 다시 로그인해 주세요.\");location.href=\"logout\"</script>";
        }else if(userService.changePw(userDTO, changePw).equals("notEqualOldPw")){
            return "<script>alert(\"기존 비밀번호가 틀립니다. 다시 확인해 주세요.\");history.back();</script>";
        }else if(userService.changePw(userDTO, changePw).equals("notEqualNewPw")){
            return "<script>alert(\"입력한 비밀번호가 다릅니다. 다시 확인해 주세요.\");history.back();</script>";
        }else{
            return "<script>alert(\"오류가 발생했습니다. 다시 로그인해 주세요\");location.href=\"/logout\"</script>";
        }
    }
}

package com.web.admin.loginauth;

import com.web.admin.service.UserLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
@Slf4j
@Component
@RequiredArgsConstructor
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final UserLogService userLogService;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;
        String fail_id = request.getParameter("id");

        if(exception instanceof UsernameNotFoundException){
            errorMessage = "UsernameNotFound";
        }else if(exception instanceof BadCredentialsException){
            errorMessage = "BadCredentials";
            userLogService.getUserLog2(fail_id,fail_id+" 로그인 실패");
        }else{
            errorMessage = "LoginFailed";
        }



        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");
        setDefaultFailureUrl("/login?error=true&errorMessage="+errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}

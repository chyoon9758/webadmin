package com.web.admin.loginauth;

import com.web.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationSuccessHandler AuthSuccessHandler;
    private final AuthenticationFailureHandler AuthFailureHandler;
    private final UserService userService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .headers().disable()
                .exceptionHandling().accessDeniedPage("/access-denied");

        http.authorizeHttpRequests()
                .antMatchers("/login", "/css/**", "/vendor/**", "/transcode/event","/system/healthcheck").permitAll()
                .antMatchers("/dashboard/config/**").hasAnyAuthority("ROLE_1", "ROLE_2")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard/monitor/main")
                .usernameParameter("id")
                .passwordParameter("password")
                .loginProcessingUrl("/login")
                .failureHandler(AuthFailureHandler)
                .successHandler(AuthSuccessHandler)
                .permitAll();

        http.csrf()
                .disable();

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);

        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/login");
        /*
        http.exceptionHandling()
                .accessDeniedPage();
         */
        return http.build();
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userService);
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
}

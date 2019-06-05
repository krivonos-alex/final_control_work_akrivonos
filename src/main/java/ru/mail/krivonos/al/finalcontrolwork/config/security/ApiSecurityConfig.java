package ru.mail.krivonos.al.finalcontrolwork.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import ru.mail.krivonos.al.finalcontrolwork.config.security.handler.ApiAccessDeniedHandler;

import static ru.mail.krivonos.al.finalcontrolwork.constant.URLConstants.API_ADMIN_URL;
import static ru.mail.krivonos.al.finalcontrolwork.constant.URLConstants.API_CUSTOMER_URL;
import static ru.mail.krivonos.al.finalcontrolwork.repository.model.enums.UserRoleEnum.ADMINISTRATOR;
import static ru.mail.krivonos.al.finalcontrolwork.repository.model.enums.UserRoleEnum.CUSTOMER;

@Configuration
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApiSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher(API_ADMIN_URL)
                .authorizeRequests()
                .anyRequest()
                .hasAuthority(ADMINISTRATOR.name())
                .and()
                .antMatcher(API_CUSTOMER_URL)
                .authorizeRequests()
                .anyRequest()
                .hasAuthority(CUSTOMER.name())
                .and()
                .httpBasic()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .csrf()
                .disable();
    }

    @Bean("apiAccessDeniedHandler")
    public AccessDeniedHandler accessDeniedHandler() {
        return new ApiAccessDeniedHandler();
    }
}

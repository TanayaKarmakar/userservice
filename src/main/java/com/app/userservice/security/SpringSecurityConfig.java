package com.app.userservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
    public SecurityFilterChain filteringCriteria(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorize ->
                authorize.anyRequest().permitAll());
        return httpSecurity.build();
    }

}

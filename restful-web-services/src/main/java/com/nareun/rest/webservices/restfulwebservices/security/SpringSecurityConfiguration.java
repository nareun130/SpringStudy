package com.nareun.rest.webservices.restfulwebservices.security;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1. 모든 요청이 인증되어야 한다.
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        // 2. 인증되지 않았거나 자격증명이 되지 않으면 기본 페이지 나타내줌.
        http.httpBasic(withDefaults());
        // 3. CSRF -> POST, PUT
        http.csrf().disable();

        return http.build();
    }
}

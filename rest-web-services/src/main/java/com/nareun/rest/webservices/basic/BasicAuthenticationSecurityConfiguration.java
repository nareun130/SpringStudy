package com.nareun.rest.webservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthenticationSecurityConfiguration {

    // Filter Chain
    // authenticate all requests
    // basic authentication
    // disable csrf
    // stateless rest api

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // * 모든 http요청이 인증되어야 한다는 걸 정의
        http.authorizeRequests(
                auth -> auth.anyRequest().authenticated());
        // * http 기본 인증 설정 -> 팝업
        http.httpBasic(Customizer.withDefaults());
        // * 상태가 없는 세션을 만들어줌. -> server side에 client와 server의 동작, 상태정보를 저장하지 않는 형태,
        // server의 응답이 client와의 세션 상태와 독립적임
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // ! CSRF를 비활성화 하기 위해서는 세션에 상태가 없어야 한다.
        http.csrf().disable();

        return http.build();
    }

}

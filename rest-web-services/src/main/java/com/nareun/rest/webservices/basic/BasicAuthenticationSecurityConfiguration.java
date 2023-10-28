package com.nareun.rest.webservices.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

// @Configuration // * -> jwt를 사용할 때는 이 어노테이션을 해제
public class BasicAuthenticationSecurityConfiguration {

    // Filter Chain
    // authenticate all requests
    // basic authentication
    // disable csrf
    // stateless rest api

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // ?클라이언트 요청 처리를 위한 2가지
        // 1. preflight처리 -> OPTIONS허용
        // 2. 기본 인증 구현. 로그인 시 헤더를 생성해서 REST

        // * 모든 http요청이 인증되어야 한다는 걸 정의
        http.authorizeRequests(
                auth -> auth
                        // 1. 모든 url에 대한 OPTIONS 메서드 허용
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated());
        // * http 기본 인증 설정 -> 팝업
        http.httpBasic(Customizer.withDefaults());
        // * 상태가 없는 세션을 만들어줌.
        // * -> 세션 정보나 쿠키 정보를 별도로 저장하고 관리하지 않기 때문에 API서버는 들어오는 요청만을 단순히 처리하면 됨. 서비스 자유도
        // 높아지고, 서버의 구현이 단순해짐.
        // server의 응답이 client와의 세션 상태와 독립적임
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // ! CSRF를 비활성화 하기 위해서는 세션에 상태가 없어야 한다.
        http.csrf().disable();
        return http.build();

        // ~> 위의 내용 빌더 패턴으로 생성 가능

    }

}

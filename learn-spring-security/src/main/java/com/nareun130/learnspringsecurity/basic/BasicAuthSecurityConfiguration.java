package com.nareun130.learnspringsecurity.basic;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BasicAuthSecurityConfiguration {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// HttpSecurity : 특정 HTTP 요청에대해 웹 기반 보안 설정 가능
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		// 기본 formLogin사용x
//		http.formLogin(withDefaults()); 

		// * 세션 사용 해제 -> STATELES하게 만들어 준다.
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(withDefaults());
		// * csrf 사용 해제 -> session을 사용하지 않을거기 때문
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
}

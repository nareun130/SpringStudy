package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // 웹 보안 설정 구성
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtTokenFilter jwtTokenFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.authorizeHttpRequests(auth -> auth
//				
				.requestMatchers(new AntPathRequestMatcher("/test/**"))
				.permitAll()
				// 관리자 관련 모든 요청에 대해 ADMIN권한이 있는 사용자만 허용
				.requestMatchers(new AntPathRequestMatcher("/api/v1/admin/**"),
						new AntPathRequestMatcher("/api/v2/admin/**"))
				.hasRole("ADMIN")
				// 회원 가입 및 로그인 관련 모든 요청에 대해 아무나 승인
				.requestMatchers(new AntPathRequestMatcher("/api/v1/auth/**"),
						new AntPathRequestMatcher("/api/v2/auth/**"))
				.permitAll()
				// 중복 체크 관련 모든 요청에 대해 아무나 허용
				.requestMatchers(new AntPathRequestMatcher("/api/v1/user/check/**"),
						new AntPathRequestMatcher("/api/v2/user/check/**"))
				.permitAll()
				// 유저정보 관련 모든 요청에 대해 승인된 사용자만 허용
				.requestMatchers(new AntPathRequestMatcher("/api/v1/user/**"),
						new AntPathRequestMatcher("/api/v2/user/**"))
				.permitAll()
				// 첨부파일 관련 GET요청에 대해 아무나 승인
				.requestMatchers(new AntPathRequestMatcher("/api/v1/attachment/**", "GET"),
						new AntPathRequestMatcher("/api/v2/attachment/**", "GET"))
				.permitAll()
				// 댓글관련 GET요청에 대해 아무나 승인
				.requestMatchers(new AntPathRequestMatcher("/api/v1/commnet/**", "GET"),
						new AntPathRequestMatcher("/api/v2/commnet/**", "GET"))
				.permitAll()
				// 게시글 관련 GET 요청에 대해 아무나 승인
				.requestMatchers(new AntPathRequestMatcher("/api/v1/post/**", "GET"),
						new AntPathRequestMatcher("/api/v1/post/**", "GET"))
				.permitAll()
				// 기타 모든 GET 요청에 대해 승인된 사용자만 허용
				// ! 모든 GET요청을 검사하므로 마지막에 추가
				.requestMatchers(new AntPathRequestMatcher("/api/v1/**"), new AntPathRequestMatcher("/api/v2/**"))
				.authenticated()
				.requestMatchers(new AntPathRequestMatcher("/**","OPTIONS"))
				.permitAll()
				)
				.csrf(AbstractHttpConfigurer::disable)
				//TODO
				.httpBasic(Customizer.withDefaults())
				.headers(headers->headers.frameOptions(frameOptions->frameOptions.sameOrigin()))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// jwtTokenFilter를 이용하여 Token의 유효성을 검사
				.addFilterBefore(this.jwtTokenFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

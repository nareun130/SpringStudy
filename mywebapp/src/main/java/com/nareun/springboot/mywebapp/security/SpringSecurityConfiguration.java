package com.nareun.springboot.mywebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
    // LDAP or Database
    // In Memory

    // InMemoryUserDetailsManager
    // InMemoryUserDetailsManager(UserDetails... users)

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        UserDetails userDetails1 = createNewUser("nareun", "1234");
        UserDetails userDetails2 = createNewUser("gogo", "1029");

        // ~> 이렇게 원하는 만큼 사용자 추가 가능
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {

        // * String을 받아서 String을 리턴 */
        // ! 어떤 input이 와도 인코딩한다음 사용자 세부정보를 설정할거다.
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);

        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();
        return userDetails;
    }

    // * Spring의 Password 인코더 설정 */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    // * */ Spring Security 기본값
    // 1. 모든 URL 요청 보호
    // 2. 인증되지 않은 요청에 대해 로그인 폼이 보여짐

    // * */ h2 console에 접속을 위해
    // CSRF disable
    // H2 consle 페이지 ->HTML Frames
    // ! Spring Security는 기본적으로 CSRF diable & HTML Frames 허용 x
    // ~> SecurityFilterChain이 맡음 -> 이 설정을 변경!

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // ? 기본 설정 -> 모든 요청 승인
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        // ? formLogin() 기본값 활성화 -> 승인 되지 않은 request는 login창
        http.formLogin(Customizer.withDefaults());
        // * Customizer.withDefaults() -> 기존 security 설정

        // ~> lambda식으로 변환
        // http.csrf().disable();
        http.csrf(csrf -> csrf.disable());

        // http.headers().frameOptions().disable();
        http.headers((hedaers) -> hedaers.frameOptions((frameOptions) -> frameOptions.disable()));

        return http.build();
    }
}

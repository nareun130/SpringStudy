package com.nareun.springboot.mywebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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

}

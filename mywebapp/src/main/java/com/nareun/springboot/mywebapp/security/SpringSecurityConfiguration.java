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
        // * String을 받아서 String을 리턴 */
        //! 어떤 input이 와도 인코딩한다음 사용자 세부정보를 설정할거다.
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
 
        // UserDetails userDetails = User.withDefaultPasswordEncoder() -> Deprecated
        UserDetails userDetails = User.builder().passwordEncoder(passwordEncoder)
                .username("nareun")
                .password("1234")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    // * Spring의 Password 인코더 설정 */
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}

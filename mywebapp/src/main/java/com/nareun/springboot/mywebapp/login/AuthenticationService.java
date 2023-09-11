package com.nareun.springboot.mywebapp.login;

import org.springframework.stereotype.Service;

@Service //~> 비즈니스 논리를 가지기 때문에 @Service => 이렇게 bean설정 안 해주면 주입이 X
public class AuthenticationService {
    public boolean authenticate(String username, String password) {

        boolean isValidUserName = username.equalsIgnoreCase("nareun");
        boolean isValidPassword = password.equalsIgnoreCase("dummy");

        return isValidUserName && isValidPassword;

    };
}

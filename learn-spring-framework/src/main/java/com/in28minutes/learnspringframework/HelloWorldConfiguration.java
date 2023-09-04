package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean //* 이렇게 Bean을 생성하여 Spring 컨테이너에서 관리하게 됨. */
    public String name(){
        return "nareun";
    }
} 

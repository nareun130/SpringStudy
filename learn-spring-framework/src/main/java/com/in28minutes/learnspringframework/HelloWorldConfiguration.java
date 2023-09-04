package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//? record :java 클래스를 만들 때 lombok의 @Data 같은  기능 - jdk 16부터 사용가능
record Person(String name, int age){};
record Address(String nation, String city){};
@Configuration
public class HelloWorldConfiguration {

    @Bean //* 이렇게 Bean을 생성하여 Spring 컨테이너에서 관리하게 됨. */
    public String name(){
        return "nareun";
    }

    @Bean
    public int age(){
        return 25;
    }

    @Bean
    public Person person(){
        return new Person("seongho", 25);
    }
    @Bean
    public Address address(){
        return new Address("Korea", "seoul");
    }


} 

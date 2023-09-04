package com.in28minutes.learnspringframework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        // * 1. 스프링 컨텍스트 실행 단게 - 설정 클래스를 사용하여 Spring 컨텍스트 시작 가능*/
        var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        // * 2. 스프링이 관리해야하는 것을 설정 - @Configuration */
        //HelloWorldConfiguration - @Configuration
        //name - @Bean
        
        // 여러 방법이 있지만 메서드 이름으로 bean을 가져옴.);
        System.out.println(context.getBean("name"));
        
    }
}

package com.in28minutes.learnspringframework.examples.a1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan // ? 아무것도 없으면 현재 패키지에서 자동으로 스캔
public class DepInjectionLauncerApplication {

    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(DepInjectionLauncerApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        }

    }

}

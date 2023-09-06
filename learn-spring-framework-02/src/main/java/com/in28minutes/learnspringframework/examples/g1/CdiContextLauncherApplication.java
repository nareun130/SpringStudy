package com.in28minutes.learnspringframework.examples.g1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.inject.Inject;
import jakarta.inject.Named;

// @Component
@Named //~> jakarta CDI 어노테이션으로 Spring 어노테이션 대체 
//! 필수 X
class BusinessService{
    private DataService dataService;

    public DataService getDataService() {
        return dataService;
    }

    // @Autowired
    @Inject
    public void setDataService(DataService dataService) {
        System.out.println("Setter Injection");
        this.dataService = dataService;
    }
    
}

// @Component
@Named
class DataService{

}
@Configuration
@ComponentScan // ? 아무것도 없으면 현재 패키지에서 자동으로 스캔
public class CdiContextLauncherApplication {

    public static void main(String[] args) {
        //? 텅 빈 런처
        try (var context = new AnnotationConfigApplicationContext(CdiContextLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            
            System.out.println(context.getBean(BusinessService.class).getDataService());
        }

    }

}

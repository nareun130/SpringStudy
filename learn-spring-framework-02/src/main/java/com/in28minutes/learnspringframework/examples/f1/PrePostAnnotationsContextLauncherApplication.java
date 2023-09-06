package com.in28minutes.learnspringframework.examples.f1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
class SomeClass{
    private SomeDependency someDependency;

    public SomeClass(SomeDependency someDependency){
        super(); 
        this.someDependency = someDependency;
        System.out.println("All dependencies are ready!");
    }

    @PostConstruct //* 의존성을 연결하는 대로 초기화를 실행 */
    //~> 다른 Bean이 이 Bean을 사용할 수 있게 되기 전에 이 메서드가 호출됨.
    //? => DB등에서 데이터를 가져오려는 경우에 사용 가능
    public void initalize(){
        someDependency.getReady();
    }

    @PreDestroy//* 컨텍스트에서 Bean이 삭제되기 전에 실행  */
    //~> DB등에 연결되어 있다면 cleanUp()으로 종료
    //?  => 보유하고 있던 리소스를 해제하는데 일반적으로 사용
    public void cleanUp(){
        System.out.println("CleanUp");
    }

}

@Component
class SomeDependency{

    public void getReady() {
        System.out.println("Some logic using SomeDepedency ");
    }

}

@Configuration
@ComponentScan // ? 아무것도 없으면 현재 패키지에서 자동으로 스캔
public class PrePostAnnotationsContextLauncherApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(PrePostAnnotationsContextLauncherApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        }

    }

}

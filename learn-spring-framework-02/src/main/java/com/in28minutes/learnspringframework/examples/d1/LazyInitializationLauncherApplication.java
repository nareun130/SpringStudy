package com.in28minutes.learnspringframework.examples.d1;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//!Spring bean의 초기화는 기본적으로 : Eager(즉시 초기화) -> Eager를 추천!!
//! Spring 구성에 오류가 있을 경우 즉시 초기화를 사용하면 애플리케이션이 시작할 대 오류를 바로 확인 가능 
@Component
class ClassA {
    //  public ClassA(){
    //     System.out.println("ClassA Bean initalized");
    //  }
}

@Component
@Lazy //~> 이제 ClassB Bean 을 사용할 때 초기화가 됨. 권장X, 자주 사용 X
//! 지연 초기화를 사용하는 경우 애플리케이션 시작 때 Spring 구성 오류 발생 X
class ClassB {
  
    private ClassA classA;

    public ClassB(ClassA classA){
        //*여기에 다양한 Logic을 놓을 수 있다. */
        System.out.println("Some Initialization logic");
        //~> Bean을 로드하지 않고 Bean에서 메서드를 호출하지 않더라도 자동으로 Bean이 초기화 됨.
        //? 이렇게 자동으로 Bean을 초기화 시키지 않기 위해서 @Lazy 사용
        this.classA = classA;
    }
    
    public void doSomething(){
        System.out.println("Do Something");
    }  
}
 
@Configuration
@ComponentScan // ? 아무것도 없으면 현재 패키지에서 자동으로 스캔
public class LazyInitializationLauncherApplication {

    public static void main(String[] args) {
        // ? 텅 빈 런처
        try (var context = new AnnotationConfigApplicationContext(LazyInitializationLauncherApplication.class)) {
            System.out.println("Initialization of context is complete");
            //이 때 classB의 Bean이 로드됨!
            context. getBean(ClassB.class).doSomething();

        }

    }

}

package com.in28minutes.learnspringframework.examples.e1;

import java.util.Arrays;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component//! 기본적으로 Spring에서 생성되는 모든 bean은 싱글톤!!
class NormalClass {

}

@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
// ~> 프로토 타입 클래스가 됨. -> Bean 참조 때마다 다른 객체 생성
@Component
class PrototypeClass {

}

@Configuration
@ComponentScan // ? 아무것도 없으면 현재 패키지에서 자동으로 스캔
public class BeanScopesLauncherApplication {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(BeanScopesLauncherApplication.class)) {
            //NormalClass의 Instance는 전부 같다.
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));
            System.out.println(context.getBean(NormalClass.class));

            //전부 다 다른 객체
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
            System.out.println(context.getBean(PrototypeClass.class));
        }

    }

}

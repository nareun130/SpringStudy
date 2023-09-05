package com.in28minutes.learnspringframework.examples.a1;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
class YourBusinessClass {
    

    //* 1. 필드 주입 */
    Dependency1 dependency1;

    Dependency2 dependency2;

    @Override
    public String toString() {

        //! @Autowired가 없으면 null 이 찍힘.
        return "Using " + dependency1 + " and " + dependency2;
    }

    //* 3. 생성자 주입 */
    // @Autowired
    //~> Autowired를 추가하지 않아도 여기 있는 모든 의존성으로 생성자를 생성하면 자동으로 생성자를 사용하여 객체를 만듦
    public YourBusinessClass(Dependency1 dependency1, Dependency2 dependency2) {
        super();
        System.out.println("Constructor Injection - YourBusinessClass");
        this.dependency1 = dependency1;
        this.dependency2 = dependency2;
    }

    //* 2. 수정자 주입 */
    // @Autowired
    // public void setDependency1(Dependency1 dependency1) {
    //     System.out.println("Setter Injection - setDependency1");
    //     this.dependency1 = dependency1;
    // }

    // @Autowired
    // public void setDependency2(Dependency2 dependency2) {
    //     System.out.println("Setter Injection - setDependency2");
    //      this.dependency2 = dependency2;
    // }
    
}

@Component
class Dependency1 {

}

@Component
class Dependency2 {

}

@Configuration
@ComponentScan
public class DepInjectionLauncerApplication {

    public static void main(String[] args) {

        try (var context = new AnnotationConfigApplicationContext(DepInjectionLauncerApplication.class)) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

            System.out.println(context.getBean(YourBusinessClass.class));
        }

    }

}

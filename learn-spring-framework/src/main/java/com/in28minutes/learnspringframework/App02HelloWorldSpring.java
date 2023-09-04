package com.in28minutes.learnspringframework;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {
    public static void main(String[] args) {
        // * 1. 스프링 컨텍스트 실행 단게 - 설정 클래스를 사용하여 Spring 컨텍스트 시작 가능*/
        var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class);
        // * 2. 스프링이 관리해야하는 것을 설정 - @Configuration */
        // HelloWorldConfiguration - @Configuration
        // name - @Bean

        // 여러 방법이 있지만 메서드 이름으로 bean을 가져옴.);
        System.out.println(context.getBean("name"));
        System.out.println(context.getBean("age"));
        System.out.println(context.getBean("person"));
        System.out.println(context.getBean("person2MethodCall"));
        System.out.println(context.getBean("person3Parameters"));
        // System.out.println(context.getBean("address"));// bean의 name을 변경 하였으므로 오류
        System.out.println(context.getBean("address2"));
        System.out.println(context.getBean(Person.class));//3개의 충돌 - person,person2MethodCall,person3Parameters
        // ? class 이름으로도 찾을 수 있다.
        System.out.println(context.getBean(Address.class));//Bean의 유형을 검색하기 때문에 2개가
        // 검색됨.
        System.out.println(context.getBean("person5Qualifier"));

        // ? 함수형으로 context의 모든 bean을 리스트화 시켜서 출력
        // Arrays.stream(context.getBeanDefinitionNames())
        //         .forEach(System.out::println);// *이런 거를 메서드 참조라고 함. */

    }
}

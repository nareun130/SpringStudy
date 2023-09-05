package com.in28minutes.learnspringframework.helloworld;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//? record :java 클래스를 만들 때 lombok의 @Data 같은  기능 - jdk 16부터 사용가능
record Person(String name, int age, Address address) {
};

record Address(String nation, String city) {
};

@Configuration
public class HelloWorldConfiguration {

    @Bean // * 이렇게 Bean을 생성하여 Spring 컨테이너에서 관리하게 됨. */
    public String name() {
        return "nareun";
    }

    @Bean
    public int age() {
        return 25;
    }

    @Bean
    public Person person() {
        return new Person("seongho", 25, new Address("Korea", "seoul"));
    }

    // * 기존의 Spring Bean과 관계가 있는 새로운 Spring Bean을 만든다. - 2가지*/
    // 1. method 호출 - Spring의 bean을 사용
    @Bean
    public Person person2MethodCall() {
        return new Person(name(), age(), address());
    }

    // 2. 매개변수를 사용 -> Spring Bean을 생성하면서 기존의 Bean이 Parameter로 사용됨.
    @Bean
    // public Person person3Parameters(String name, int age, Address address2)
    // {//name, age ,address2
    public Person person3Parameters(String name, int age, Address address3) {// name, age ,address2
        // ! -> method가 address여도 address2를 활용해야함! : bean name이 adress2라서
        // return new Person(name, age, address2); // method호출 대신 paratmeter를 추가
        return new Person(name, age, address3);
    }

    @Bean
    @Primary
    public Person person4Parameters(String name, int age, Address address) {
        return new Person(name, age, address); // ! address인 bean은 없다. -> 어떻게 될까?
        //-> No qualifying bean of type 'com.in28minutes.learnspringframework.Address'
        // available: expected single matching bean but found 2: address2,address3 발생
        //? 일치하는 후보가 여러 개인 시나리오에서는 Spring에서 예외를 출력 -> @Primary로 하나를 기본으로 만들어줌.
        //?+ @Qualifier라는 방법도 있다.
    }

    
    @Bean
    public Person person5Qualifier(String name, int age, @Qualifier("address3qualifier") Address address){
        return new Person(name, age, address);
    }

    @Bean(name = "address2")
    @Primary
    public Address address() {
        return new Address("Korea", "seoul");
    }

    @Bean(name = "address3")
    @Qualifier("address3qualifier")
    public Address address3() {
        return new Address("Korea", "seoulCity");
    }

}

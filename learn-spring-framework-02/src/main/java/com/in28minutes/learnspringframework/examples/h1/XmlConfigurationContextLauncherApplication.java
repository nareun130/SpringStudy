package com.in28minutes.learnspringframework.examples.h1;

import java.util.Arrays;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.in28minutes.learnspringframework.game.GameRunner;

// @Configuration -> java 설정 대신 xml을 쓸 것이다.
@ComponentScan
public class XmlConfigurationContextLauncherApplication {
 
    public static void main(String[] args) {

        //* java 설정 클래스가 아닌 xml 설정으로 application Context 생성 */
        try (var context = new ClassPathXmlApplicationContext("contextConfiguration.xml")) {
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
            System.out.println(context.getBean("name"));   
            System.out.println(context.getBean("age")); 

            context.getBean(GameRunner.class).run();  
        } 

    }

}

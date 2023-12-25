package com.nareun.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @SpringBootConfiguration : @Configuration에서 추가 bean 등록 가능 하게 함 
 * 
 * @EnableAutoConfiguration
 * 
 * @ComponentScan : @Component, @Service, @Repository, @Controller and oters
 * 	-> 자동 빈 등록 
 */
@SpringBootApplication
public class BasicApplication {

	public static void main(String[] args) {
		// BasicApplication.class가 있는 패키지들을 전부분석해서 Bean 등록
		SpringApplication.run(BasicApplication.class, args);
	}

}

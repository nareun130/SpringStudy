package com.nareun130.learnspringaop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nareun130.learnspringaop.aop.business.BusinessService1;
import com.nareun130.learnspringaop.aop.business.BusinessService2;

@SpringBootApplication
public class LearnSpringAopApplication implements CommandLineRunner {

	// * CommandLineRunner : 구동 시점에 실행되는 코드가 자바 문자열 아규먼트 배열에 접근해야할 필요가 있는 경우에 사용
	private Logger logger = LoggerFactory.getLogger(getClass());

	private BusinessService1 businessService1;
	private BusinessService2 businessService2;

	public LearnSpringAopApplication(BusinessService1 businessService1, BusinessService2 businessService2) {
		this.businessService1 = businessService1;
		this.businessService2 = businessService2;
	}

	public static void main(String[] args) {
		SpringApplication.run(LearnSpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Value returned is {}", businessService1.calculateMax());
		logger.info("Value returned is {}",businessService2.calculateMin());
	}

}

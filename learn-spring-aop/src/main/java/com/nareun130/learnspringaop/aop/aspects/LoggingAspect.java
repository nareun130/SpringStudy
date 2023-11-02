package com.nareun130.learnspringaop.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration 
@Aspect
public class LoggingAspect {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//@Pointcut - When?
	//? execution(* PACAKAGE.*.*(..)) : 패키지안의 모든 메서드를 인터셉트 해라 
	//execution(*com.nareun130.learnspringaop.aop.business.*.*(..))
	@Before("execution(* com.nareun130.learnspringaop.aop.*.*.*(..))")
	public void logMethodCall(JoinPoint joinpoint) {
		//Logic - What?
		logger.info("Before Aspect - Method is called - {}", joinpoint);
	}
}

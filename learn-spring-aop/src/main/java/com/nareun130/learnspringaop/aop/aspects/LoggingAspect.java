package com.nareun130.learnspringaop.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(getClass());

	// @Pointcut - When?
	// ? execution(* PACAKAGE.*.*(..)) : 패키지안의 모든 메서드를 인터셉트 해라
	// execution(*com.nareun130.learnspringaop.aop.business.*.*(..))
	@Before("execution(* com.nareun130.learnspringaop.aop.*" + ".*.*(..))")
	public void logMethodCallBeforeExecution(JoinPoint joinpoint) {
		// Logic - What?
		logger.info("Before Aspect - Method is called {} with arguments : {}", joinpoint, joinpoint.getArgs());

	}

	@After("execution(* com.nareun130.learnspringaop.aop.*" + ".*.*(..))")
	public void logMethodCallAfterExecution(JoinPoint joinpoint) {
		// Logic - What?
		logger.info("After Aspect - Method is called {} has executed", joinpoint);
	}

	@AfterThrowing(pointcut = "execution(* com.nareun130.learnspringaop.aop.*" + ".*.*(..))", throwing = "exception")
	public void logMethodCallAfterException(JoinPoint joinpoint, Exception exception) {
		//? throwing의 exceptionr과 Exception exception이 연결됨.
		// Logic - What?
		logger.info("AfterThrowing Aspect - Method is called {} has thrown an exception {}", joinpoint, exception);
	}
	
	@AfterReturning(pointcut = "execution(* com.nareun130.learnspringaop.aop.*" + ".*.*(..))", returning = "resultValue")
	public void logMethodCallAfterSuccessfulExecution(JoinPoint joinpoint, Object resultValue) {
		//? throwing의 exceptionr과 Exception exception이 연결됨.
		// Logic - What?
		logger.info("AfterReturning Aspect - Method is called {} has returned {}", joinpoint, resultValue);
	}
}

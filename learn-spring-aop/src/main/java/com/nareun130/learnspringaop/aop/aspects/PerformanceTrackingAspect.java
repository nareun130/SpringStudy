package com.nareun130.learnspringaop.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceTrackingAspect {
	
	private final String TRACKTIME_ANNOTATION_PATH = "com.nareun130.learnspringaop.aop.aspects.CommonPointcutConfig.trackTimeAnnotation()";
	private Logger logger = LoggerFactory.getLogger(getClass());

//	@Around("execution(* com.nareun130.learnspringaop.aop.*.*.*(..))")
	@Around(value = TRACKTIME_ANNOTATION_PATH)
	public Object findExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		// ? JoinPoint는 메서드를 실행시켜주지 않아서 ProceedingJoinPoint를 쓴다.

		// Start a timer
		long startTimeMillis = System.currentTimeMillis();

		// Execute the method
		Object returnValue = proceedingJoinPoint.proceed();

		// Stop the timer
		long stopTimeMillis = System.currentTimeMillis();

		long executionDuration = stopTimeMillis - startTimeMillis;

		logger.info("Around Aspect - {} Method excecuted in {}ms", proceedingJoinPoint, executionDuration);

		return returnValue;
	}
}

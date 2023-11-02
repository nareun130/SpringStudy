package com.nareun130.learnspringaop.aop.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointcutConfig {
	//? 공용 포인트 컷 정의
	//! 만약 실행 메서드의 위치가 바뀐다면? -> 포인트컷을 일일이 다 정의? 
	//~> 공용 포인트컷 설정 
	
	@Pointcut("execution(* com.nareun130.learnspringaop.aop.*" + ".*.*(..))")
	public void businessAndDataPackageConfig() {
		
	}
	@Pointcut("execution(* com.nareun130.learnspringaop.aop.business" + ".*.*(..))")
	public void businessPackageConfig() {
		
	}
	@Pointcut("execution(* com.nareun130.learnspringaop.aop.data" + ".*.*(..))")
	public void dataPackageConfig() {
		
	}
	
	@Pointcut("bean(*Service*)") // ? 간단하게 Service가 들어간 bean을 대상으로 해준다.
	public void allPackageConfigUsringBean() {
		
	}
	
	@Pointcut("@annotation(com.nareun130.learnspringaop.aop.annotations.TrackTime)")
	public void trackTimeAnnotation() {
		
	}
}

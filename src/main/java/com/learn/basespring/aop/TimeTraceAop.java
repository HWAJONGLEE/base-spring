package com.learn.basespring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {
	
	
	// aop 코드가 실행할 패키지경로
	@Around("execution(* learn.basespring..*(..))")
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		System.out.println("Start : " + joinPoint.toString());
		try {
			return joinPoint.proceed();
		} finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("End: " + joinPoint.toString() + " " + timeMs);
		}
		
	}
}

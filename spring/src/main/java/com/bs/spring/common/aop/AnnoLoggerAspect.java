package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Component //bean으로 등록
@Aspect //aspect 클래스로 등록
@Slf4j
public class AnnoLoggerAspect {

	@Pointcut("execution(* com.bs.spring..*(..))") 
	public void test() {}  //껍데기 메소드
	
	@Pointcut("within(com.bs.spring..service.*)")
	public void test2() {}
	
//	@Before("test()")
	public void loggerTest(JoinPoint jp) {
		log.debug("---------------- anno aspect before 실행 ---------------");
		
	}
	
	@After("test2()")
	public void loggerTest2(JoinPoint jp) {
		log.debug("=================== test2 after 실행 =====================");
		Signature sig = jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+"."+sig.getName());
		Object[] params = jp.getArgs();
		if(params!=null) {
			for(Object o : params) {
				log.debug("{}",o);
			}
		}
	}
	
	//Around 적용하기
	
	@Around("within(com.bs.spring..dao.*)")
	public Object daoTest(ProceedingJoinPoint pj) throws Throwable{
		//전, 후 로직을 한 번에 설정할 수 있음.
		//지역변수를 전, 후 로직에서 공유할 수 있음.
		
		//전, 후를 구분하는 구분자는 
		//ProceedingJoinPoint 클래스가 제공하는 proceed()메소드 호출.
		// -> 반환 오브젝트
		// 전 : proceed() 메소드를 호출하기 전 로직.
		// 후 : proceed() 메소드를 호출한 후 로직.
		
		log.debug(" before=-=-=-=-=-=-=-= around before log =-=-=-=-=-=-=-=");
		StopWatch watch = new StopWatch();
		watch.start();
		
		
		Object obj = pj.proceed();
		
		log.debug(" after=-=-=-=-=-=-=-= around after log =-=-=-=-=-=-=-=");
		watch.stop();
		log.debug("실행 시간 : " + watch.getTotalTimeMillis() + "ms");
		
		return obj;
	}
	
	//AfterThrowing 설정하기
	@AfterThrowing(value="within(com.bs.spring..controller.*)",
					throwing="e")
	public void afterThrowingLogger(JoinPoint jp, Throwable e) {
		log.debug("============== 비상비상 에러 발생 ============");
	Signature sig = jp.getSignature();
	log.debug(sig.getDeclaringTypeName()+"."+sig.getName() +"에서 에러 발생**");
	log.debug("에러 내용 : "+ e.getMessage());
	StackTraceElement[] stackTrace = e.getStackTrace();
	for(StackTraceElement se : stackTrace) {
		log.debug("{}",se);
	}
	}
	
}

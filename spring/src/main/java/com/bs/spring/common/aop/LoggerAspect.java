package com.bs.spring.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

//공통의 관심 기능을 관리하는 클래스
@Slf4j
public class LoggerAspect {
	
	//공통의 기능(메소드)을 정의
	//메소드 선언 방식이 정해져있음.
	//Before, After, AfterReturning, ...
	//void 메소드명(JoinPoint)
	
	public void loggerBefore(JoinPoint jp) {
		log.debug("----------------- aop before 메소드 실행 --------------------");
		//실행되는 메소드에 대한 정보 확인
		// JoinPoint클래스가 반환하는 Signature클래스 이용
		// getSignature() 메소드를 이용
		Signature sig = jp.getSignature();
		log.debug(sig.getDeclaringTypeName());
		/*
		 * Class targetClass = sig.getDeclaringType(); //클래스로 받아서 클래스에 대한 정보 확인 가능
		 * targetClass.getAnnotations();
		 */
		log.debug(sig.getName());
		
		//메소드 실행할 때 전달된 인수들을 확인하기
		Object[] params = jp.getArgs();
		if(params!=null) {
			for(Object o : params) {
				log.debug("{}",o);
			}
		}
		
		log.debug("=============================================================");
		
	}

	public void loggerAfter(JoinPoint jp) {
		log.debug("--------------------- after aspect 실행 ------------------------------");
		Signature sig = jp.getSignature();
		log.debug(sig.getDeclaringTypeName()+"."+sig.getName());
		log.debug("=============================================================");
	}
	
	//Around
	//Object 메소드명(ProceedingJoinPoint) throws Throwable
	
	//
}

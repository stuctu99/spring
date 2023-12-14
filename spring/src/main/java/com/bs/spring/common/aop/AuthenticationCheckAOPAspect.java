package com.bs.spring.common.aop;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.exception.BadAuthenticationException;
import com.bs.spring.member.model.dto.Member;

//@Component
//@Aspect
public class AuthenticationCheckAOPAspect {

	@Before("execution(* com.bs.spring.memo..*(..))")
	public void checkAdmin(JoinPoint jp) {
		//로그인 정보를 확인하고 로그인한 사용자가 admin이면 사용 가능
		//아니면 불가넝 에러 발생
		HttpSession session = (HttpSession)RequestContextHolder.currentRequestAttributes()
		.resolveReference(RequestAttributes.REFERENCE_SESSION);
		
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember==null || !loginMember.getUserId().equals("admin")){
			//실행되면 안됨
			
			throw new BadAuthenticationException("권한이 부족합니다:(", LocalDate.now());
			
		}
		
	}
}

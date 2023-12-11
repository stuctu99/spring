package com.bs.spring.common.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

//	private Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	//-> 이거 대신 lombok에서 제공하는 @Slf4j로 log 필드 생성 가능. 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//매핑된 매핑메소드를 실행하기 전에 실행할 로직을 설정.
		//true : 매핑 메소드 정상 실행.
		//false : 매핑 메소드 실행하지 않음.
		log.debug("-------------- 인터셉터 prehandle 실행 -----------------");
		log.debug(request.getRequestURI());
		log.debug("--------------------------------------------------------");
		//Object handler : 실행될 메소드에 대한 정보를 저장한 객체.
		//		   클래스와 메소드의 정보를 가져올 수 있음.
		
		HandlerMethod hm = (HandlerMethod)handler;
		//실행 클래스 가져오기
		Object controller = hm.getBean(); //Controller 클래스
		log.debug("클래스 : {}",controller);
		
		Method m = hm.getMethod();
		log.debug("메소드명 : " + m.getName());
		
		return true;
//		return false;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.debug("-------------- 인터셉터 postHandle 실행 -----------------");
		log.debug("응답화면  :" + modelAndView.getViewName());
		Map modelData = modelAndView.getModel();
		log.debug("model : {}",modelData);
		log.debug("--------------------------------------------------------");
	
	}

	//alt shift s v

	
	
}

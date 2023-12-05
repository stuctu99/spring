package com.bs.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

	@RequestMapping("/demo/demo.do")  
	public String demoPage() {
//	public void demoPage() {
		//경로 + view 이름만 반환 -> 자동으로 viewResolver가 jsp를 찾아서 반환.
		// /WEB-INF/views/+반환값+.jsp -> RequestDispatcher.forward()
		return "demo/demo";
	}
	
	//void면 확장자 빼고 중간에있는 주소를 가지고 매핑해줌.
//	public void demoPage() {
//		
//	}
	
	
	//맵핑 메소드 선언하기
	//반환형(리턴값), 매개변수, 어노테이션 알아보기
	
	//1. 반환형
	//	1) String : '/WEB-INF/views/'를 제외한 '경로 + view 이름(jsp이름)'
	//				등록된 InternalResourceViewResolver가 jsp를 찾아서 반환해줌
	//	2) void : URL주소와 view의 경로+이름이 같거나, HttpServletResponse로 직접 응담을 한 경우 
	//	3) ModelAndView : view 화면에 전달할 데이터와 view를 하나의 객체에 묶어서 viewresolver에 전달함.
	//	4) 클래스타입 : 객체 자체를 응답하는 형식 -> json converter를 이용해서 변경하여 응답함.
	//					* 메소드에||반환형에 @ResponseBody 어노테이션을 추가.
	
	//2. 매개변수
	//	1) HttpServletRequest : 서블릿의 그녀석임..
	//	2) HttpServletResponse : 서블릿의 그녀석임..
	//	3) HttpSession : 서블릿의 그녀석...
	//	4) java.util.Locale : 서버의 로케일 정보를 저장한 객체
	//	5) InputStream/Reader : 읽어오는 스트림 객체
	//	6) OutputStream/Writer : 보내는 스트림 객체(클라이언트)
	//	7) 기본 자료형 변수 : 클라이언트가 보낸 Parameter데이터를 저장할 변수를 선언 -> 자동 대입
	//		매개변수명을 name값과 동일하게 설정해주거나 
	//		@RequestParam을 이용해서 매핑처리해줌. -> 타입에 맞춰 형변환해줌.
	//		매개변수가 잘못된 경우 BadRequest에러가 발생.
	
				
	
	
	
}

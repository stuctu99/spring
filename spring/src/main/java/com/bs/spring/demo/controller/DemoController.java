package com.bs.spring.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.demo.model.dto.Demo;

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
	//	8) 클래스타입(DTO) : Data베이스와 연동해서 데이터를 저장하는 객체. 
	//		클라이언트가 전달한 parameter데이터와 매핑해서 객체를 생성해줌. -> command
	//		* 객체의 필드와 parameter의 name값이 같은 데이터 매핑.
	//	9) java.util.Map : 클라이언트가 보낸 데이터를 map으로 처리함. * 모두 단일값으로 받는다.
	
	//	10) @RequestHeader(name값) : header정보를 가져올 수 있음.
	//	11) @CookieValue(name값) : 쿠키에 저장되어있는 값을 가져올 수 있음.
	
	//	매핑 메소드에 사용하는 추가 어노테이션
	//	12) @ResponseBody : 객체를 응답할 때 사용하는 어노테이션. java.class -> json
	//	13) @RequestBody : 요청 body에 json 데이터가 있으면 지정된 객체로 변환해서 저장. json -> java.class
	//	14) @ModelAttribute : model 저장 관리 -> hibernate를 이용한 validator
	//	15) @SessionAttribute(key값) : Httpsession에 저장된 특정 값을 가져올 때 사용.
	
	//	16) Model, ModelAndView
	
	//서블릿과 동일하게 이용하기
	@RequestMapping("/demo/demo1.do")
//	public void demo1(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	public String demo1(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

		//		System.out.println(request);
//		System.out.println(response);
		String devName = request.getParameter("devName");
		int devAge = Integer.parseInt(request.getParameter("devAge"));
		String devGender = request.getParameter("devGender");
		String devEmail = request.getParameter("devEmail");
		String[] devLang = request.getParameterValues("devLang");
		
		Demo d= Demo.builder()
				.devAge(devAge)
				.devEmail(devEmail)
				.devName(devName)
				.devGender(devGender)
				.devLang(devLang)
				.build();
		
		System.out.println(d);
		
		request.setAttribute("demo", d);

		//request.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(request, response);
		return "demo/demoResult";
		
		//return "demo/demoResult";
		// /WEB-INF/views/demo/demoResult.jsp.forward(req,res);
	}
	
	
	
}

package com.bs.spring.demo.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.bs.spring.common.ExcelDemoListConvert;
import com.bs.spring.demo.model.dto.Address;
import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.model.service.DemoService;

@Controller
public class DemoController {
	
	private WebApplicationContext context;
	
	
	private DemoService service;
	
	public DemoController(DemoService service, WebApplicationContext context) {
		this.service=service;
		this.context=context;
	}
	
	@RequestMapping("/demo/exceldownload")
	public View downloadDemoExcel(Model model) {
		List<Demo> demoList = service.selectDemoList();
		model.addAttribute("demoList", demoList);
		
		return new ExcelDemoListConvert();
		
	}

	//국제화 메세지 출력하기
	@GetMapping("/message")
	public String getGreeting(Model m) {
		m.addAttribute("greetingKr",context.getMessage("greeting",null,Locale.KOREA));
		m.addAttribute("greetingUs",context.getMessage("greeting",null,Locale.US));
	
		
		m.addAttribute("testUs", 
						context.getMessage("test.msg", new Object[] {"BY Lee", "happy"}, Locale.US));
		m.addAttribute("testUs", 
				context.getMessage("test.msg", new Object[] {"이보연", "행복"}, Locale.KOREA));

		return "common/message";
	}
	
	@RequestMapping("/demo/demo.do")  
	public String demoPage() {
//	public void demoPage() {
		//경로 + view 이름만 반환 -> 자동으로 viewResolver가 jsp를 찾아서 반환.
		// /WEB-INF/views/+반환값+.jsp -> RequestDispatcher.forward()
//		int su=0;
//		if(su==0) {
//			throw new IllegalArgumentException("에러러에러러");
//		}
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
	
	//클라이언트가 보낸 데이터를 매개변수와 매칭하여 전달받기
	//클라이언트가 보낸 데이터의 name값과 선언한 매개변수의 변수명이 동일해야함.
	@RequestMapping("/demo/demo2.do")
	public String demo2(String devName, int devAge, String devEmail, String devGender, String[] devLang,
							Model model) {
		
		System.out.println(devName + devAge + devEmail + devGender + Arrays.toString(devLang));
		
		Demo d = Demo.builder()
						.devName(devName)
						.devGender(devGender)
						.devAge(devAge)
						.devEmail(devEmail)
						.devLang(devLang)
						.build();
		
		//스프링에서 데이터를 저장할 수 있는 클래스를 제공.
		// Model 클래스 -> request와 생명주기가 동일함.
		// addAttribute() 메소드를 이용 -> key, value 형식으로 데이터를 저장
		// addAttribute("key",Object);
		model.addAttribute("demo",d);
		
		
		
		return "demo/demoResult";
	
	}
	
	//클라이언트가 보낸 데이터에 대한 옵션 설정하기
	//@RequestParam 어노테이션을 이용
	//속성값을 설정함.
	//value, defaultValue, required
	
	@RequestMapping("/demo/demo3.do")
	public String requestParamTest(
					@RequestParam(value="devName") String name,
					@RequestParam(value="devAge", defaultValue="10") int age,
					@RequestParam(value="devEmail") String email,
					@RequestParam(value="devGender", required=false) String gender,
					@RequestParam (value="devLang", required=true) String[] lang,
					Model model) {
		
		Demo d = Demo.builder()
				.devName(name)
				.devAge(age)
				.devEmail(email)
				.devGender(gender)
				.devLang(lang)
				.build();
		
		model.addAttribute("demo", d);
		
		
		return "demo/demoResult";
	}
	
	//DTO객체로 직접 클라이언트가 보낸 데이터 받기
	//클라이언트가 보낸 데이터의 key(name)값과 필드명이 일치해야 함.
	//Date타입에 태해 java.sql.Date타입은 변경해서 대입해줌. java.util.Date는 parse 못함.
	
	@RequestMapping(value="/demo/demo4.do", method=RequestMethod.POST)
	public String commandMapping(@ModelAttribute Demo demo, Address address, Model m) {
		demo.setAddress(address);
		System.out.println(demo);
		m.addAttribute("demo",demo);
		return "demo/demoResult";
		
	}
	
	//Map으로 클라이언트가 보낸 데이터 받기
	@RequestMapping("/demo/demo5.do")
	public String demo5(@RequestParam Map param, String[] devLang, Model m) {
		
		System.out.println(param);
		param.put("devLang", devLang);
		m.addAttribute("demo", param);
		
		return "demo/demoResult";
		
		
	}
	
	//추가 데이터 받아오기
	//Cookie, Header, Session에 저장된 값을 가져오기
	//Cookie : @CookieValue(value = "key") String data
	//Header : @RequestHeader(value = "key") String header
	//Session : @SessionAttribute(value = "key") String id
	
	@RequestMapping("/demo/demo6.do")
	public String extraData(
					@CookieValue(value="cookieData", required=false) String cdata,
					@RequestHeader(value="User-agent") String userAgent,
					@SessionAttribute(value="sessionId") String sessionId) {
			System.out.println("쿠키 : " + cdata);
			System.out.println("userAgent : " + userAgent);
			System.out.println("세션 : " + sessionId);
		return "index";
	}
	
	
	@RequestMapping("/demo/demo7.do")
	public ModelAndView modelViewTest(Demo d, ModelAndView mv) {
		//ModelAndView 클래스는 view, model을 한번에 저장/관리하는 객체.
		//view : setViewName()메소드를 이용해서 화면 선택.
		//data : addObject("key", Object)메소드를 이용해서 저장
		mv.setViewName("demo/demoResult");
		mv.addObject("demo", d);
		
		//매개변수 있는 생성자로 생성
//		ModelAndView mv2 = new ModelAndView("demo/demoResult","demo","d");
		
		Map data = mv.getModel();
		
		String viewName = mv.getViewName();
		
		return mv;	
	}
	
	//객체를 선언
	@RequestMapping("/demo/demo8.do")
	@ResponseBody
	public String dataReturn() {
		return "data"; //data.jsp로 가지 않고 "data"가 출력됨
		
//		return "유병승,이규홍,최경현,이지혜";
		
	}
	
	@GetMapping("/test2")
	public String getTest() {
		System.out.println("get으로 요청");
		return "index";
		
	}
	@PostMapping("/test2")
	public String postTest(){
		System.out.println("post로 요청");
		return "index";
	}
	
	//동적 주소를 설정할 수 있음.
	
	// member/admin
	// board/3 GET
	@GetMapping("/demo/{no}")
	public String searchDemo(@PathVariable String no) { //int, String...
		System.out.println(no);
		return "index";
	}
	
	//@PostMapping 써도 됨
	@RequestMapping("/demo/insertDemo.do")
	public String insertDemo(Demo d, Model model) {
		//insert는 dispatcher.forward로 응답하면 안됨! 계속 insert됨 -> PRG (post-redirect-get)
		int result = service.insertDemo(d);
		//Redirect로 응답하기
		//redirect:요청주소 (매핑주소. jsp 노노)
//		return "index";
		String msg,loc;
		if(result>0) {
			msg="입력 성공";
			loc="demo/demoList.do";
		}else {
			msg="입력 실패";
			loc="demo/demo.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
//		return "redirect:/";
		return "common/msg";
	}
	
	@RequestMapping("/demo/demoList.do")
	public void demoList(Model model) {
		List<Demo> demos = service.selectDemoList();
		model.addAttribute("demos",demos);
	}
	
	@RequestMapping("/demo/updatedemo.do")
	public String updateDemo(int devNo, Model model) {
		Demo d = service.selectDemoByNo(devNo);
		model.addAttribute("demo",d);
		model.addAttribute("langList", Arrays.asList(d.getDevLang()));
		return "demo/updateDemo";
	}
	
//	@RequestMapping(value="/demo/updateDemoend.do", method=RequestMethod.POST)
	@PostMapping("/demo/updateDemoend.do")
	public String updateDemoend(Demo d, Model model) {
		int result = service.updateDemo(d);
		String msg,loc;
		if(result>0) {
			msg="수정 성공";
			loc="demo/demoList.do";
		}else {
			msg="수정 실패";
			loc="demo/updatedemo.do?devNo="+d.getDevNo();
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		
		return "common/msg";
	}
	
	
	
	
}

package com.bs.spring.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/ajax")
@RequiredArgsConstructor
public class AjaxController {

	private final Gson gson;
	private final WebApplicationContext context;
	
	@RequestMapping("/basicajax")
	public void basicajax(HttpServletResponse response) throws IOException {
		
		List<String> names = List.of("이보연","정우현","최종민","이규홍","윤영찬");
		response.setContentType("application/json;charset=utf-8");
		gson.toJson(names, response.getWriter());
		
		
	}
	
	@RequestMapping("/basicajaxpage")
	public String basicajaxpage(Model m) {
		
		m.addAttribute("greetingKr",
	            context.getMessage("greeting",null,Locale.KOREA));
	      m.addAttribute("greetingUs",
	            context.getMessage("greeting",null,Locale.US));
	      
	      m.addAttribute("greetingJp",
	            context.getMessage("greeting",null,Locale.JAPAN));
	      
	      m.addAttribute("testUs",
	            context.getMessage("test.msg", new Object[] {"BS YOO","Tired"},Locale.US));
	      m.addAttribute("testKr",
	            context.getMessage("test.msg", new Object[] {"유병승","피곤하다, 진도가 안나간다"},Locale.KOREA));
	      m.addAttribute("testJp",
	            context.getMessage("test.msg", new Object[] {"ユ・ビョンスン","疲れている"},Locale.JAPAN));
		
		return "common/message";
	}
	
	//converter를 이용해서 json으로 java객체를 응답할 때는
	//@ResponseBody 어노테이션을 이용해서 처리.
	//메소드의 반환형을 반환할 데이터 자료형으로 설정함.
	
	@RequestMapping("/converterajax")
	@ResponseBody
	public List<String> converterTest(){
		
		return List.of("이보연","유병승","마커스");
		
	}
	
}

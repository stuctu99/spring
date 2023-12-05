package com.bs.spring.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.test.model.service.TestService;

//import lombok.RequiredArgsConstructor;

@Controller
//@RequiredArgsConstructor //final로 선언된 필드를 초기화하는 생성자 생성 
public class TestController {

//	private final TestService service;
	private TestService service;
//	private String test;

	  @Autowired
	  public TestController(TestService service) {
		  super(); this.service = service;
	  }
	 
	@RequestMapping("/test")
	public String diTest() {
		System.out.println("controller실행");
		service.testService();
		return "hello";
	}
	
}

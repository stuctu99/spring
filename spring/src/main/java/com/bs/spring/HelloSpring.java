package com.bs.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloSpring {

	public HelloSpring() { //기본생성자 ctrl + space + enter
		System.out.println("HelloSpring 생성!!");
	}
	
	@RequestMapping("/")
	public String mainPage() {
		return "hello";
	}
	
}

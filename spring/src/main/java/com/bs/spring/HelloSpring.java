package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.testmodel.Animal;
import com.bs.spring.testmodel.Food;
import com.bs.spring.testmodel.Person;
import com.bs.spring.testmodel.Shop;
import com.bs.spring.testmodel.dto.Test;

@Controller
public class HelloSpring {
	
	//log4j 객체 생성하기
	private final Logger logger = LoggerFactory.getLogger(HelloSpring.class);
	
	//springbean으로 등록된 객체 이용하기
	//필드에서 주입을 받아 이용이 가능하다. -> 
	//@Autowired
	//주입할 객체를 지정하기
	//@Qualifier("animal2")
	private Animal a;
	
	//@Autowired
	private Animal animal2;
	
	//@Autowired
	private Animal animal3;
	
	private Food food;
	
	private Animal ani;

	private Person p3;
	
	@Autowired
	private Shop shop;
	
	@Autowired
	private Test test;
	
	//생성자를 이용해서 의존성 주입하기
	public HelloSpring(@Qualifier("animal2") Animal a, Animal animal2, Animal animal3, Food food
								,Animal cat, Person p3) {
		this.a = a;
		a.setAge(4); //기본적으로는 동일한 객체(scope=singletone이 기본). a랑 animal2 둘 다 바뀜.  
					//scope="prototype"으로 주면 각각 생성됨.
		this.animal2 = animal2; 
		this.animal3 = animal3;
		this.food=food;
		this.ani=cat;
		this.p3 = p3;
	}
	
	/*
	 * public HelloSpring() { //기본생성자 ctrl + space + enter
	 * System.out.println("HelloSpring 생성!!");
	 * 
	 * }
	 */
	
	@RequestMapping("/bean")
	public String mainPage() {
		System.out.println(a);
		System.out.println(animal2);
		System.out.println(animal3);
		System.out.println(food);
		System.out.println(ani);
		System.out.println(p3);
		System.out.println(shop);
		return "hello";
	}
	
	
	
	@RequestMapping("/")
	public String index(HttpSession session, HttpServletResponse response) {
		
		//log4j를 이용해서 log 출력하기
		//1. log4j 클래스를 생성
		//2. 생성된 객체에서 제공하는 메소드를 이용
		//	level별로 메소드를 제공함
		//	debug("") : <logger>태그의 level이 debug일 때 출력 
		//	info("") : <logger>태그의 level이 info일 때 출력 
		//	warn("") : <logger>태그의 level이 warn일 때 출력 
		//	error("") : <logger>태그의 level이 error일 때 출력 
		
		
		logger.debug("debug로그");
		logger.info("info로그");
		logger.warn("warn로그");
		logger.error("error로그");
		
		Cookie c = new Cookie("cookieData","cookiecookie");
		c.setMaxAge(60*60*24);
		response.addCookie(c);
		
		//출력 대상이 문자열이 아닌 경우 패턴으로 설정
		logger.debug("쿠키 : {}", c);
		
		session.setAttribute("sessionId", "admin");
		
		return "index";
	}
	
	
	
	
}

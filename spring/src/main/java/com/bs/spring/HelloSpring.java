package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		Cookie c = new Cookie("cookieData","cookiecookie");
		c.setMaxAge(60*60*24);
		response.addCookie(c);
		
		session.setAttribute("sessionId", "admin");
		
		return "index";
	}
	
	
}

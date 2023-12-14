package com.bs.spring.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SecurityController {

	@RequestMapping("/loginpage")
	public String loginpage() {
		
		return "common/loginpage";
	}
	
	@RequestMapping("/loginfail")
	public String loginfail(Model m) {
		
		m.addAttribute("msg","로그인 실패지롱~");
		m.addAttribute("loc","/loginpage");
		return "common/msg";
	}
	
	@RequestMapping("/loginsuccess")
	public String loginsuccess() {
		log.debug("로그인 성 공");
		//로그인한 사람 정보 가져오기
		//spring-security -> SecurityContextHolder.getContext().getAuthentication().getPrincipal()
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.debug("{}",o);
		return "redirect:/";
		
	}
	
}

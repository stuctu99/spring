package com.bs.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor //final로 선언된 애들에 대해 생성자 만들어줌.
@RequestMapping("/member") //여기 선언하는 매핑메소드 앞에는 전부 /member가 붙음
@SessionAttributes({"loginMember"}) //model에 대한 key. -> session으로 관리. 중괄호 안에 쉼표로 여러개 쓸 수 있음.
public class MemberController {
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final MemberService service;
	private final BCryptPasswordEncoder passwordEncoder;
	
	
	@RequestMapping("/login.do") // -> /member/login.do
	public String login(String userId, String password, HttpSession session, Model model) {
		Member m = service.selectmemberById(userId);
		
		//비밀번호 일치 여부를 확인하려면
		//BCryptPasswordEncoder클래스가 제공하는 메소드를 이용
		//matches("원본값","암호화된 값") -> true, false
		logger.debug(userId);
		logger.debug(password);
		
		
//		if(m!=null && m.getPassword().equals(password)) {
		if(m!=null&&passwordEncoder.matches(password,m.getPassword())) {
			//로그인 성공
			//session.setAttribute("loginMember",m); 
			model.addAttribute("loginMember",m);
//			System.out.println("로그인!!"+m);
			logger.debug("{}",m);
		}else {
			//로그인 실패
			model.addAttribute("msg","로그인 실패");
			model.addAttribute("loc","/");
			return "common/msg";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
//	public String logout(HttpSession session) {
	public String logout(SessionStatus session) {
		
//		if(session!=null) session.invalidate();
		if(!session.isComplete()) {
			session.setComplete();
		}
		
		return "redirect:/";
		
	}

	@RequestMapping("/enrollMember.do")
	public void enrollMember(Member m, Model model) {
		
//		return "member/enrollMember";
		
	}
	
	@RequestMapping("/enrollMemberEnd.do")
	public String enrollMemberEnd(Member m, Model model) {
		
		//BCryptPasswordEncoder클래스가 제공하는 메소드를 이용해서 단방향 암호화하기
		//encode()메소드를 이용
		String oriPw = m.getPassword();
		String encPw = passwordEncoder.encode(oriPw);
		System.out.println(oriPw);
		System.out.println(encPw);
		
		m.setPassword(encPw);
	
		
		System.out.println("mmmm  " + m);
		System.out.println("model  "+ model);
		
//		m.setName(userName);
		
		int result= service.insertMember(m);
		
		String msg, loc;
		

		
		if(result>0) {
			msg="등록 성공";
			loc="demo/demoList.do";
		}else {
			msg="등록 실패";
			loc="demo/demo.do";
		}
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		return "common/msg";
		
	}
	
	@RequestMapping("/memberView.do")
	public String memberView(String userId, String password, Model model) {
		Member m = service.selectmemberById(userId);
		
		model.addAttribute("member", m);
		
		return "memberView";
	}
	
	
}

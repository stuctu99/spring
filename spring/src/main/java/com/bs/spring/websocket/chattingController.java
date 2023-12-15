package com.bs.spring.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class chattingController {

	@GetMapping("/chatpage")
	public String chatpage() {
		return "chatting/chattingpage";
	}
	
}

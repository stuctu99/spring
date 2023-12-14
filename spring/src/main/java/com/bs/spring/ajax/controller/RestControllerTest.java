package com.bs.spring.ajax.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;

//import com.bs.spring.demo.model.dto.Demo;

/*@Controller
@ResponseBody 두 개 합친거 -> @RestController*/
@RestController
@RequiredArgsConstructor
public class RestControllerTest {

//	@GetMapping("/testdata")
//	public Demo test() {
//		return Demo.builder()
//					.devName("이보연")
//					.devAge(28)
//					.devGender("F")
//					.devEmail("bo@bobo")
//					.devLang(new String[] {"c","java"})
//					.build();
//	}
	
	private final BoardService boardService;
	private final MemberService memberService;
	
	
	@GetMapping("/boards")
	public List<Board> selectBoard(
						@RequestParam(defaultValue="1") int cPage, 
						@RequestParam(defaultValue="10") int numPerpage){
		return boardService.selectBoardList(Map.of("cPage",cPage,"numPerpage",numPerpage));
		
	}

	@PostMapping(value="/member", consumes = MediaType.APPLICATION_JSON_VALUE)
	public Member insertMember(@RequestBody Member m) {
		int result = memberService.insertMember(m);
		return result>0?m:null;
		
	}
	
}

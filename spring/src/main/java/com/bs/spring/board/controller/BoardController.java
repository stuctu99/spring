package com.bs.spring.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;
import com.bs.spring.demo.model.dto.Demo;

@Controller
@RequestMapping("/board")
public class BoardController {

	private BoardService service;
	private PageFactory pageFactory;
	
	public BoardController(BoardService service, PageFactory pageFactory) {
		this.service=service;
		this.pageFactory=pageFactory;
	}
	
	@RequestMapping("/board.do")
	public void selectBoardList(@RequestParam(defaultValue = "1")int cPage,
								@RequestParam(defaultValue = "5")int numPerpage,
								Model model) {
		List<Board> boards = service.selectBoardList(Map.of("cPage", cPage, "numPerpage", numPerpage));
		
		int totalData = service.selectBoardCount();
		
		model.addAttribute("pageBar", pageFactory.getPage(cPage, numPerpage, totalData, "board.do"));
		
		model.addAttribute("boards", boards);
		
		
	}
	
	@RequestMapping("/boardView.do")
	public String selectBoard(int boardNo, Model model) {
	
		Board board = service.selectBoard(boardNo);
		
		model.addAttribute("board",board);
		
		return "board/boardView";
	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard() {
		return "board/boardWrite";
	}
	
	@RequestMapping("/insertBoardEnd.do")
	public String insertBoardEnd(Board d, Model model) {
		
		int result = service.insertBoard(d);
		
		String msg,loc;
		if(result>0) {
			msg="등록 성공";
			loc="board/board.do";
		}else {
			msg="등록 실패";
			loc="board/boardWrite.do";
		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		
		return "common/msg";
	}
	
	


	
	

}

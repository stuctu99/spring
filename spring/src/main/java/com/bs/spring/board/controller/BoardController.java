package com.bs.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;
import com.bs.spring.member.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
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
	public String insertBoardEnd(MultipartFile upFile[], Board b, String boardWriter, Model model, HttpSession session) {
		
//		log.debug(upFile.getOriginalFilename());
//		log.debug(upFile.getName());
//		log.debug("{}",upFile.getSize());
		
		//MultipartFile객체를 이용해서 파일 저장하기
		//MultipartFile객체가 제공하는 transferTo() 메소드를 이용
		//1. 저장할 위치 -> 절대경로
		String path = session.getServletContext().getRealPath("/resources/upload/board");
		//2. 파일이름을 재정의 -> renamedFile name을 설정 -> 중복되지않게 설정
		//파일명을 재정의 로직을 구성하기
		
		List<Attachment> files = new ArrayList<>();
		
		if(upFile!=null) {
			for(MultipartFile mf:upFile) {
				
			
//			if(!upFile.isEmpty()) {
			if(!mf.isEmpty()) {
//				String oriName = upFile.getOriginalFilename();
				String oriName = mf.getOriginalFilename();
				String ext = oriName.substring(oriName.lastIndexOf(".")); //.txt .mp3
				Date today = new Date(System.currentTimeMillis());
				int randomNum = (int)(Math.random()*10000)+1;
				String rename = "bobo_"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(today)+"_"+randomNum + ext;
				
				try {
//					upFile.transferTo(new File(path, rename));
					mf.transferTo(new File(path, rename));
					Attachment file = Attachment.builder()
											.originalFilename(oriName)
											.renamedFilename(rename)
											.build();
					files.add(file);
					
				}catch(IOException e) {
					e.printStackTrace();
				}
				
				}	
			}
		}
		b.setFiles(files);
		
		b.setWriter(Member.builder().userId(boardWriter).build());
		
		log.debug("{}",b);
		String msg,loc;
		
		try {
			int result = service.insertBoard(b);
			msg="등록 성공";
			loc="board/board.do";
		}catch(RuntimeException e) {
			msg="등록 실패";
			loc="board/insertBoard.do";
			for(Attachment a : files) {
				File delFile = new File(path, a.getRenamedFilename());
				delFile.delete();
			}
		}
		
		
//		String msg,loc;
//		if(result>0) {
//			msg="등록 성공";
//			loc="board/board.do";
//		}else {
//			msg="등록 실패";
//			loc="board/boardWrite.do";
//		}
		
		model.addAttribute("msg",msg);
		model.addAttribute("loc",loc);
		
		return "common/msg";
	}
	
	


	
	

}

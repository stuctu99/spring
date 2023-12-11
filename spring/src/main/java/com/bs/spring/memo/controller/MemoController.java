package com.bs.spring.memo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.common.PageFactory;
import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.model.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {

	private MemoServiceImpl service;
	private PageFactory pageFactory;
	public MemoController(MemoServiceImpl service, PageFactory pageFactory) {
		
		this.service = service;
		this.pageFactory = pageFactory;
	}
	
	
	@RequestMapping("/memo.do")
	public void selectMemoList(@RequestParam(defaultValue="1")int cPage, 
								@RequestParam(defaultValue="5")int numPerpage,
								Model model,
								@ModelAttribute("memo") Memo memo) {
		
		List<Memo> memos = service.selectMemoList(Map.of("cPage", cPage, "numPerpage", numPerpage));
		
		int totalData=service.selectMemoCount();
		

		model.addAttribute("pageBar",pageFactory.getPage(cPage,numPerpage,totalData,"memo.do"));
		
		model.addAttribute("memos", memos);
		
		
	}
	
	@RequestMapping("/insertMemo.do")
	public String insertMemo(@Validated Memo m, 
							BindingResult isResult, 
							Model model,
							@RequestParam(defaultValue="1")int cPage, 
							@RequestParam(defaultValue="5")int numPerpage) {
		
		if(isResult.hasErrors()) {
			
			model.addAttribute("memos",service.selectMemoList(Map.of("cPage", cPage, "numPerpage", numPerpage)));
			return "/memo/memo";
		}
		service.insertMemo(m);
		
		return "redirect:/memo/memo.do";
	}
	
	
}

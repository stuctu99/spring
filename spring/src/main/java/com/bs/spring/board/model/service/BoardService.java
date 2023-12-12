package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.dto.Board;

public interface BoardService {

	public Board selectBoard(int boardNo);
	
	public List<Board> selectBoardList(Map<String, Integer> page);
	public int selectBoardCount();
	
	public int insertBoard(Board b);
	


}

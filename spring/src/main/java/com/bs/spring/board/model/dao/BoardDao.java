package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.board.model.dto.Board;

public interface BoardDao {


	public Board selectBoard(SqlSession session, int boardNo);
	
	public List<Board> selectBoardList(SqlSession session, Map<String, Integer> page);
	public int selectBoardCount(SqlSession session);
	
	public int insertBoard(SqlSession session, Board b);
	
	
}

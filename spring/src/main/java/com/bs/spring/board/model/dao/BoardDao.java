package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

public interface BoardDao {

	Board selectBoard(SqlSession session, int boardNo);
	
	List<Board> selectBoardList(SqlSession session, Map<String, Integer> page);
	int selectBoardCount(SqlSession session);
	
	int insertBoard(SqlSession session, Board b);
	
	int insertAttachment(SqlSession session, Attachment attachment);
	
}

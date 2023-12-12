package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	private final SqlSession session;
	private final BoardDao dao;
	
	
	@Override
	public Board selectBoard(int boardNo) {
		return dao.selectBoard(session, boardNo);
	}

	@Override
	public List<Board> selectBoardList(Map<String, Integer> page) {
		return dao.selectBoardList(session, page);
	}

	@Override
	public int selectBoardCount() {
		return dao.selectBoardCount(session);
	}

	@Override
	public int insertBoard(Board b) {
		return dao.insertBoard(session, b);
	}

	
}

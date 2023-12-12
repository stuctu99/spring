package com.bs.spring.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.dto.Board;


@Repository
public class BoardDaoImpl implements BoardDao {

	
	@Override
	public Board selectBoard(SqlSession session, int boardNo) {
		return session.selectOne("board.selectBoard",boardNo);
	}

	@Override
	public List<Board> selectBoardList(SqlSession session, Map<String, Integer> page) {
		int cPage = (Integer)page.get("cPage");
		int numPerpage = (Integer)page.get("numPerpage");
		RowBounds rb = new RowBounds((cPage-1)*numPerpage, numPerpage);
		return session.selectList("board.selectBoardList",null,rb);
	}

	@Override
	public int selectBoardCount(SqlSession session) {
		return session.selectOne("board.selectBoardCount");
	}

	@Override
	public int insertBoard(SqlSession session, Board b) {
		return session.insert("board.insertBoard", b);
	}

	
}

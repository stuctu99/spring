package com.bs.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public int insertBoard(Board b) {
		//게시글, 첨부파일을 저장하기
		int result = dao.insertBoard(session, b);
		
		if(result>0) {
			if(b.getFiles().size()>0) {
				b.getFiles().forEach(file->{
					file.setBoardNo(b.getBoardNo());
					int fileResult =dao.insertAttachment(session, file);
					if(fileResult==0) throw new RuntimeException("첨부파일 등록 실패");
				});
			}
		
		}else {
			throw new RuntimeException("첨부파일 등록 실패");
		}
		return result;
	}

	
}

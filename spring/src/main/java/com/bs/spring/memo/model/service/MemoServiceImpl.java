package com.bs.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService{
	
	private final SqlSession session;
	private final MemoDao dao;
	
	public List<Memo> selectMemoList(Map<String, Integer> page) {
		return dao.selectMemoList(session, page);
	}

	@Override
	public int selectMemoCount() {
		return dao.selectMemoCount(session);
	}

	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session, m);
	}

	

	
	
}

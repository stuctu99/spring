package com.bs.spring.memo.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;

public interface MemoService {

	public List<Memo> selectMemoList(Map<String, Integer> page);
	
	
	public int selectMemoCount();
	
	public int insertMemo(Memo m);
	
}

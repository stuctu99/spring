package com.bs.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.memo.model.dto.Memo;

public interface MemoDao {

	
	public List<Memo> selectMemoList(SqlSession session, Map<String, Integer> page);
	
	public int selectMemoCount(SqlSession session);
	
	public int insertMemo(SqlSession session, Memo m);
	
	
}

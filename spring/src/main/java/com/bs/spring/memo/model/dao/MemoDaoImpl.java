package com.bs.spring.memo.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.memo.model.dto.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Override
	public List<Memo> selectMemoList(SqlSession session, Map<String, Integer> page) {
		int cPage = (Integer)page.get("cPage");
		int numPerpage = (Integer)page.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage, numPerpage);
		return session.selectList("memo.selectMemoList",null, rb);
	}

	@Override
	public int selectMemoCount(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectOne("memo.selectMemoCount");
	}

	@Override
	public int insertMemo(SqlSession session, Memo m) {
		return session.insert("memo.insertMemo",m);
	}

	
}

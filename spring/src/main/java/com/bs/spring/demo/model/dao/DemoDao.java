package com.bs.spring.demo.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.demo.model.dto.Demo;

@Repository
public class DemoDao {

	public int insertDemo(SqlSession session, Demo demo) {
		return session.insert("demo.insertDemo", demo);
	}
	
	public List<Demo> selectDemoList(SqlSession session){
		return session.selectList("demo.selectDemoList");
		
	}
}

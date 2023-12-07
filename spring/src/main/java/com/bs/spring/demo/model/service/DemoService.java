package com.bs.spring.demo.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.model.dao.DemoDao;
import com.bs.spring.demo.model.dto.Demo;

@Service
public class DemoService {

	private SqlSession session;
	private DemoDao dao;
	
	
	public DemoService(SqlSession session, DemoDao dao) {
		super();
		this.session = session;
		this.dao = dao;
	}

	public int insertDemo(Demo d) {
		return dao.insertDemo(session, d);
	}
	
	public List<Demo> selectDemoList(){
		return dao.selectDemoList(session);
	}
}

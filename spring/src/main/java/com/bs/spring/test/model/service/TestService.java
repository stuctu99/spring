package com.bs.spring.test.model.service;

import org.springframework.stereotype.Service;

import com.bs.spring.test.model.dao.TestDao;

@Service
public class TestService {

	private TestDao dao;

	public TestService(TestDao dao) {
		this.dao=dao;
	}
	
	public void testService() {
		System.out.println("서비스 실행");
		dao.testDao();
		
	}
}

package com.bs.spring.member.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	private final SqlSession session;
	private final MemberDao dao;
	
	
	@Override
	public Member selectmemberById(String userId) {
		return dao.selectMemberById(session, userId);
	}

	@Override
	public List<Member> selectMember() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> searchMember(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectMemberCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session, m);
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}

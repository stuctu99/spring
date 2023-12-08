package com.bs.spring.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.member.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member selectMemberById(SqlSession session, String userId) {
		return session.selectOne("member.selectMemberById", userId);
	}

	@Override
	public List<Member> selectMember(SqlSession session) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Member> searchMember(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectMemberCount(SqlSession session) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMember(SqlSession session, Member m) {
		return session.insert("member.insertMember",m);
	}

	@Override
	public int updateMember(SqlSession session, Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(SqlSession session, String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}

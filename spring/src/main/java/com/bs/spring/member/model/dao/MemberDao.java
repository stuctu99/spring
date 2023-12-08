package com.bs.spring.member.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.member.model.dto.Member;

public interface MemberDao {

	Member selectMemberById(SqlSession session, String userId);
	List<Member> selectMember(SqlSession session);
	List<Member> searchMember(SqlSession session, Map<String,Object> param);
	
	int selectMemberCount(SqlSession session);
	
	int insertMember(SqlSession session, Member m);
	int updateMember(SqlSession session, Member m);
	int deleteMember(SqlSession session, String userId);
	
	
}

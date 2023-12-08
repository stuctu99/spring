package com.bs.spring.member.model.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.member.model.dto.Member;

public interface MemberService {

	Member selectmemberById(String userId);
	List<Member> selectMember();
	List<Member> searchMember(Map<String,Object> param);
	
	int selectMemberCount();
	
	int insertMember(Member m);
	int updateMember(Member m);
	int deleteMember(String userId);
	
}

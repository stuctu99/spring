package com.bs.spring.security.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SecurityLoginService implements UserDetailsService{

	private final MemberDao dao;
	private final SqlSession session;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		Member loginMember=dao.selectMemberById(session, username);
		
		return loginMember;
	}

	
}

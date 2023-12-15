package com.bs.spring.member.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member implements UserDetails{
	
	@NotEmpty(message = "아이디는 반드시 입력하세요.")
	@Size(min=4, message = "아이디는 4글자 이상 입력하세요.")
	private String userId;
	@Pattern(regexp = "(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[~!@#$%^&*()_+-=])[a-zA-z~!@#$%^&*()_+-=]{8,}"
					,message = "비밀번호는 소문자, 대문자, 특수기호를 포함한 8글자 이상으로 작성하세요.")
	private String password;
	private String name;
	private String gender;
	@Min(value = 14, message = "나이는 14세 이상 입력하세요.")
	@Max(value = 120, message = "120세 이상은..ㅠ 죄송합니다.")
	private int age;
	@Email
	private String email;
	private String phone;
	private String address;
	private List<String> hobby;
	private Date enrollDate;
	
//	private boolean active;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> auth = new ArrayList<>();
		//계정별 권한 설정 -> admin, user
		if(userId.equals("admin")) auth.add(new SimpleGrantedAuthority("admin")); 
		auth.add(new SimpleGrantedAuthority("user"));
		return auth;
	}
	
	@Override
	public String getUsername() {
		return this.userId; //아이디값
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}

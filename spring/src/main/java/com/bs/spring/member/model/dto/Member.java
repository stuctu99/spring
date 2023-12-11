package com.bs.spring.member.model.dto;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Member {

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
	
}

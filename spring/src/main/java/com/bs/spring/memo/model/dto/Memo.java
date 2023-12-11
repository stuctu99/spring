package com.bs.spring.memo.model.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {

	private int memoNo;
	@NotEmpty(message="메모 내용은 반드시 작성해야합니다.")
	@Size(min=5, message="메세지는 5글자 이상 작성하세요.")
	private String memo;
	@NotEmpty(message="비밀번호를 반드시 설정하세요.")
	@Pattern(regexp="(?=.*?[0-9])(?=.*?[a-zA-Z])[a-zA-Z0-9]{4,}",message="4글자 이상 숫자, 영문자로 설정하세요.")
	private String password;
	private Date memoDate;
	
}

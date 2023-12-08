package com.bs.spring.member.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class Member {

	private String userId;
	private String password;
	private String name;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private List<String> hobby;
	private Date enrollDate;
	
}

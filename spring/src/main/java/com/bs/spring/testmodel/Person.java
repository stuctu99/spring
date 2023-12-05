package com.bs.spring.testmodel;

import lombok.Data;

@Data
public class Person {
	private String name;
	private int age;
	private String address;
	private Animal animal;
	private Food food;

	public void initTest() {
		System.out.println("생성 후 실행하는 메소드");
		System.out.println(this.toString());
	}
	
	public void destroyTest() {
		System.out.println("소멸 후 실행하는 메소드");
		System.out.println(this.toString());
	}
}

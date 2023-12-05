package com.bs.spring.testmodel;

import lombok.Data;

@Data
public class Animal {
	private String type;
	private String name;
	private int age;
	private double weight;
	
	
	public Animal() {
		System.out.println(this + "객체 생성");
	}

	
	//alt + shift + s + o
	public Animal(String type, String name, int age, double weight) {
		this.type = type;
		this.name = name;
		this.age = age;
		this.weight=weight;
		System.out.println(this+ "매개변수 있는 생성자");
	}

}


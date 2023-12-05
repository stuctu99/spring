package com.bs.spring.testmodel;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component //Food클래스를 일반 bean으로 등록
public class Food {
	private String name;
	private int price;
	private String type;
	private Animal a;
	
	
	public Food(@Qualifier("animal2") Animal a) {
		super();
		this.a = a;
	}
	
	
}


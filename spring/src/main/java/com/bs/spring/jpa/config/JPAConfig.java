package com.bs.spring.jpa.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//jpa 적용에 필요한 객체를 설정해주는 클래스
@Configuration
public class JPAConfig {

	//jpa를 이용하려면 EntityManager클래스가 필요함.
	// EntityManagerFactory 클래스에서 createEntityManager() 메소드를 이용해서 EntityManager클래스를 생성.
	// EntityManagerFactory 클래스는 Persistence클래스가 제공하는 static메소드 createEntityManagerFactory() 메소드를 이용해서 생성.
	// createEntityManagerFactory()메소드의 인수로 persistence.xml에 설정한 
	// persistence-unit의 name값을 대입한다.
	
	@Bean 
	public EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("bstest");
		return factory;
		
	}
	
	@Bean
	public EntityManager entityManager(@Autowired EntityManagerFactory factory) {
		return factory.createEntityManager();
	}
	
	
}

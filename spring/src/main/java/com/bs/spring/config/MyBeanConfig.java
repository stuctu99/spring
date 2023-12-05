package com.bs.spring.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

import com.bs.spring.testmodel.Animal;
import com.bs.spring.testmodel.Food;
import com.bs.spring.testmodel.Person;
import com.bs.spring.testmodel.Shop;

import lombok.Data;

//spring bean configuration.xml 과 같은 의미
@Configuration
@ComponentScan(basePackages = "com.bs.spring",
				includeFilters = @ComponentScan.Filter(
						type=FilterType.REGEX ,pattern={"com.bs.spring.testmodel.dto.*"}
						))//선언된 일반 클래스를 bean으로 등록할 때 사용
//@Import : 다른 환경(springbean)을 불러와 사용할 때 설정 
public class MyBeanConfig {
	//spring bean을 설정할 수 있다.
	//객체를 반환하는 메소드를 선언하고 @Bean 어노테이션을 설정한다.
	//메소드명이 bean의 id가 됨.
	@Bean(name = "cat")
	@Order(1)
	public Animal ani() {
		return new Animal("고양이","야옹이",3,8.3);
		
	}

	@Bean
	public Person p3(Food f) {
		Person p = new Person();
		Animal a = ani();
		p.setAnimal(a);
		p.setFood(f);
		return p;
	}
	
	@Bean 
	public Shop shop(List<Animal> animals) {
		return Shop.builder().name("bshop").animals(animals).build();
	}
	
//	//JDBC datasource를 생성
//	@Data
//	public BasicDataSource DataSource() {
//		BasicDataSource source = new BasicDataSource();
//	source.setDriverClassName();
//	source.setUrl();
//	source.setUserName();
//	source.setPassword();
//	return source;
//	
//	}
//	
//	@Bean
//	public Gson gson() {
//		return new Gson();
//		
//	}
}

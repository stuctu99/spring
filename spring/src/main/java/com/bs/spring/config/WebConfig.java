package com.bs.spring.config;

import java.util.Properties;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.bs.spring.common.exception.BadAuthenticationException;


@Configuration
@EnableAspectJAutoProxy
public class WebConfig {

	//국제화처리 메세지 출력하기
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean
	public HandlerExceptionResolver handlerExceptionResolver() {
		Properties exceptionProp = new Properties();
		
		exceptionProp.setProperty(BadAuthenticationException.class.getName(), 
									"/common/error");
		
		//추가적인 exception 페이지 설정 가능
		
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
		
		exceptionResolver.setExceptionMappings(exceptionProp);
		
		exceptionResolver.setDefaultErrorView("common/defaulterror");
		
		return exceptionResolver;
	
	}
}

package com.bs.spring.common.exception;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BadAuthenticationException extends RuntimeException {

	private LocalDate date;
	
	public BadAuthenticationException(String msg, LocalDate date) {
		super(msg);
		this.date=date;
	}
}

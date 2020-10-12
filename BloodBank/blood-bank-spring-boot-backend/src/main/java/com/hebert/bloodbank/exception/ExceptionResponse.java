package com.hebert.bloodbank.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
	private String message;
	private String path;
	private String error;

}
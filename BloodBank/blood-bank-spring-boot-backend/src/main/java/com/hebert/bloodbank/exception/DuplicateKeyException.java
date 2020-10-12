package com.hebert.bloodbank.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicateKeyException extends DataIntegrityViolationException{

	private static final long serialVersionUID = 3129100942901916413L;

	public DuplicateKeyException(String message){
    	super(message);
    }
}

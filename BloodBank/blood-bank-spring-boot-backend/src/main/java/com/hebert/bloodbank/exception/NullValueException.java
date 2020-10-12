package com.hebert.bloodbank.exception;

import org.springframework.transaction.TransactionSystemException;

public class NullValueException extends TransactionSystemException {
	private static final long serialVersionUID = 808988278650457056L;

	public NullValueException(String message){
    	super(message);
    }
}

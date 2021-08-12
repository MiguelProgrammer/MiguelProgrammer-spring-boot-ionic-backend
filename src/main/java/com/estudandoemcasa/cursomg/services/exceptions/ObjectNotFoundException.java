package com.estudandoemcasa.cursomg.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String sms) {
		super(sms);
	}
	
	public ObjectNotFoundException(String sms,  Throwable cause) {
		super(sms,cause);
	}

}

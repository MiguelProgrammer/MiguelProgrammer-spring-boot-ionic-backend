package com.estudandoemcasa.cursomg.services.exceptions;

public class DataIntegretyException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DataIntegretyException(String sms) {
		super(sms);
	}
	
	public DataIntegretyException(String sms,  Throwable cause) {
		super(sms,cause);
	}

}

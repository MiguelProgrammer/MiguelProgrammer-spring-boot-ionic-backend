package com.estudandoemcasa.cursomg.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.estudandoemcasa.cursomg.services.exceptions.DataIntegretyException;
import com.estudandoemcasa.cursomg.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegretyException.class)
	public ResponseEntity<StandardError> dataIntegrety(DataIntegretyException e, HttpServletRequest request){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),  System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		for(FieldError fd : e.getBindingResult().getFieldErrors()) {
			err.addError(fd.getField(), fd.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
}

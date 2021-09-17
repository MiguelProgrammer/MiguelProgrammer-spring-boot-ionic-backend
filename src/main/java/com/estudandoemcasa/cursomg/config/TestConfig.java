package com.estudandoemcasa.cursomg.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.estudandoemcasa.cursomg.services.DBService;
import com.estudandoemcasa.cursomg.services.EmailService;
import com.estudandoemcasa.cursomg.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbservice;

	@Bean
	public boolean instantiateDataBase() throws ParseException {
		dbservice.instantieateDataBase();
		return true;
	}

	@Bean
	public EmailService emailService(){
		return new MockEmailService();
	}
}

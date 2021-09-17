package com.estudandoemcasa.cursomg.services;

import org.springframework.mail.SimpleMailMessage;

import com.estudandoemcasa.cursomg.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}

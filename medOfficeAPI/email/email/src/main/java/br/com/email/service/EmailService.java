package br.com.email.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.email.dtos.EmailDto;
import br.com.email.entities.Email;
import br.com.email.entities.EmailStatus;
import br.com.email.repositories.EmailRepository;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSennder;
	@Autowired
	private EmailRepository repository;
	
	public EmailDto sendEmail(EmailDto dto) {
		Email email= new Email(dto);
		email.setSendDateEmail(LocalDateTime.now());
		email.setStatus(EmailStatus.SENT);
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom(dto.mailFrom());
		message.setTo(dto.mailTo());
		message.setSubject(dto.mailSubject());
		message.setText(dto.mailText());
		
		emailSennder.send(message);
		repository.save(email);
		return dto;
	}

	
}

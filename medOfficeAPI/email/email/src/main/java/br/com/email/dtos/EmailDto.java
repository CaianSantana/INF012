package br.com.email.dtos;

import br.com.email.entities.Email;

public record EmailDto(String mailFrom, String mailTo, String 
		mailSubject, String mailText) {
	
	public EmailDto(Email email) {
		this(email.getMailFrom(),email.getMailTo(),email.getMailSubject(),email.getMailText());
	}
}

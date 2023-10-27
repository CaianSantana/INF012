package com.br.medConsultAPI.model;

import com.br.medConsultAPI.dtos.FormConsult;
import com.br.medConsultAPI.enums.Status;
import com.br.medConsultAPI.exceptions.InvalidDataException;
import com.br.medConsultAPI.exceptions.InvalidHourException;
import com.br.medConsultAPI.exceptions.InvalidSchedulingException;
import com.br.medConsultAPI.exceptions.MinimumThirtyMinuteNoticeException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Consults")
public class Consult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String crm;
	private String cpf;
	@ManyToOne(cascade = CascadeType.ALL)
	private Scheduling scheduling;
	@Enumerated(EnumType.STRING)
	private Status status; 
	private String cancelReason;
	
	
	public Consult() {
		this.status = Status.SCHEDULED;
		this.cancelReason = null;
	}
	public Consult(FormConsult data) {
		this.crm = data.crm();
		this.cpf = data.cpf();
		this.scheduling = new Scheduling(data.scheduling());
		this.status = Status.SCHEDULED;
		this.cancelReason = null;
	}
	
	public void validateConsult() throws  InvalidSchedulingException, MinimumThirtyMinuteNoticeException, InvalidDataException, InvalidHourException{
		this.scheduling.dateValidation();
		this.scheduling.hourValidation();
		this.scheduling.consultTimeValidation();
	}
}
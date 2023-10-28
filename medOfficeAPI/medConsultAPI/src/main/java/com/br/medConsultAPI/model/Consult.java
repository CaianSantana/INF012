package com.br.medConsultAPI.model;

import java.text.ParseException;
import java.util.List;

import com.br.medConsultAPI.dtos.FormConsult;
import com.br.medConsultAPI.enums.Status;
import com.br.medConsultAPI.exceptions.DoctorCannotHaveMoreThanOneConsultAtTimeException;
import com.br.medConsultAPI.exceptions.InvalidSchedulingException;
import com.br.medConsultAPI.exceptions.MinimumThirtyMinuteNoticeException;
import com.br.medConsultAPI.exceptions.PatientOnlyHaveOneConsultPerDayException;

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
	public Consult(FormConsult data) throws ParseException {
		this.crm = data.crm();
		this.cpf = data.cpf();
		this.scheduling = new Scheduling(data.scheduling());
		this.status = Status.SCHEDULED;
		this.cancelReason = null;
	}
	
	public void validateConsult(List<Consult> list) throws  InvalidSchedulingException, MinimumThirtyMinuteNoticeException, PatientOnlyHaveOneConsultPerDayException, DoctorCannotHaveMoreThanOneConsultAtTimeException{
		this.scheduling.validateScheduling();
		for(Consult item: list) {
			if(item.getCpf().equalsIgnoreCase(this.getCpf())
					&&item.getScheduling().compareDate(this.getScheduling())) {
				throw new PatientOnlyHaveOneConsultPerDayException();
			}
			if(item.getCrm().equalsIgnoreCase(this.getCrm())
					&&item.getScheduling().compareAll(this.getScheduling())) {
				throw new DoctorCannotHaveMoreThanOneConsultAtTimeException();
			}
			}
	}
}
package com.br.medConsultAPI.model;

import com.br.medConsultAPI.dtos.FormConsult;
import com.br.medConsultAPI.enums.Status;
import com.br.medConsultAPI.exceptions.DoctorAlreadyHaveScheduledAppointmentException;

import com.br.medConsultAPI.exceptions.InactiveException;
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
	private Long doctorID;
	private Long patientID;
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
		this.doctorID = data.doctorID();
		this.patientID = data.patientID();
		this.scheduling = new Scheduling(data.scheduling());
		this.status = Status.SCHEDULED;
		this.cancelReason = null;
	}
	
	public void validateConsult() throws InactiveException, PatientOnlyHaveOneConsultPerDayException, DoctorAlreadyHaveScheduledAppointmentException{
	}
	
		
		

/*
O sistema deve possuir uma funcionalidade que permita o agendamento de consultas, na qual as seguintes informações deverão ser preenchidas:

Paciente
Médico
Data/Hora da consulta

As seguintes regras de negócio devem ser validadas pelo sistema:

O horário de funcionamento da clínica é de segunda a sábado, das 07:00 às 19:00;
As consultas tem duração fixa de 1 hora;
As consultas devem ser agendadas com antecedência mínima de 30 minutos;
Não permitir o agendamento de consultas com pacientes inativos no sistema;
Não permitir o agendamento de consultas com médicos inativos no sistema;
Não permitir o agendamento de mais de uma consulta no mesmo dia para um mesmo paciente;
Não permitir o agendamento de uma consulta com um médico que já possui outra consulta agendada na mesma data/hora;
A escolha do médico é opcional, sendo que nesse caso o sistema deve escolher aleatoriamente algum médico disponível na data/hora preenchida.
 */	
		

}
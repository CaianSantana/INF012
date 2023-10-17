package com.br.medConsultAPI.model;

import com.br.medConsultAPI.exceptions.DoctorAlreadyHaveScheduledAppointmentException;

import com.br.medConsultAPI.exceptions.InactiveException;
import com.br.medConsultAPI.exceptions.PatientOnlyHaveOneConsultPerDayException;

import dtos.FormConsult;
import dtos.FormDoctor;
import dtos.FormPatient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="Consults")
public class Consult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	private FormDoctor doctor;
	@ManyToOne(cascade = CascadeType.ALL)
	private FormPatient patient;
	@ManyToOne(cascade = CascadeType.ALL)
	private Date date;

	
	
	public Consult(FormConsult data) {
		this.doctor = data.doctor();
		this.patient = data.patient();
		this.date = data.date();
	}
	public Long getId() {
		return id;
	}
	public FormDoctor getDoctor() {
		return doctor;
	}
	public FormPatient getPatient() {
		return patient;
	}
	public Date getDate() {
		return date;
	}
	public void setDoctor(FormDoctor doctor) {
		this.doctor = doctor;
	}
	public void setPatient(FormPatient patient) {
		this.patient = patient;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void validadeConsult() throws InactiveException, PatientOnlyHaveOneConsultPerDayException, DoctorAlreadyHaveScheduledAppointmentException{
		
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
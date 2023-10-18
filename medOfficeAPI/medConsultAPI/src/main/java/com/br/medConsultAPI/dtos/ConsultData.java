package com.br.medConsultAPI.dtos;

import com.br.medConsultAPI.model.Consult;

import enums.Specialty;


public record ConsultData(Long id, Long IDDoctor, Long IDPatient, Specialty specialty, Long IDscheduling) {

	public ConsultData(Consult consult) {
		this(consult.getId(), consult.getDoctorID(), consult.getPatientID(), consult.getSpecialty(), consult.getScheduling().getID());
	}
}

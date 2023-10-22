package com.br.medConsultAPI.dtos;

import com.br.medConsultAPI.model.Consult;


public record ConsultData(Long id, Long IDDoctor, Long IDPatient, Long IDscheduling) {

	public ConsultData(Consult consult) {
		this(consult.getId(), consult.getDoctorID(), consult.getPatientID(), consult.getScheduling().getID());
	}
}

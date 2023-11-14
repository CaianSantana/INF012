package com.br.medConsultAPI.dtos;


import com.br.medConsultAPI.clients.DoctorData;
import com.br.medConsultAPI.clients.PatientData;
import com.br.medConsultAPI.model.Consult;


public record ConsultData(Long id, String doctorName, String specialty, String doctorEmail, String patientName, String patientEmail,  String schedule) {
	public ConsultData(Consult consult, DoctorData doctorData, PatientData patientData) {
		this(consult.getId(), doctorData.name(), doctorData.specialty().toString() ,doctorData.email(), patientData.name(), patientData.email(), consult.getScheduling().getSchedule().toString());
	}
}

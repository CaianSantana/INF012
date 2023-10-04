package com.br.medAppointmentAPI.dtos;


import com.br.medAppointmentAPI.models.Patient;

public record PatientData(Long id, String name, String cpf, String Phone, Long IDaddress) {

	public PatientData(Patient patient) {
		this(patient.getId() ,patient.getName(), patient.getCPF(), patient.getPhone(), patient.getAddress().getId());
	}
}


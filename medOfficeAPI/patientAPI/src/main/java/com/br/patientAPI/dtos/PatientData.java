package com.br.patientAPI.dtos;

import com.br.patientAPI.models.Patient;

public record PatientData(Long id, String name, String cpf, String Phone, Long IDaddress) {

	public PatientData(Patient patient) {
		this(patient.getId() ,patient.getName(), patient.getCpf(), patient.getPhone(), patient.getAddress().getId());
	}
}


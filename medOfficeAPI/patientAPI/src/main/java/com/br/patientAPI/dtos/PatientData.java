package com.br.patientAPI.dtos;

import com.br.patientAPI.models.Patient;

public record PatientData( String name, String cpf, String email) {

	public PatientData(Patient patient) {
		this(patient.getName(), patient.getCpf(), patient.getEmail());
	}
}


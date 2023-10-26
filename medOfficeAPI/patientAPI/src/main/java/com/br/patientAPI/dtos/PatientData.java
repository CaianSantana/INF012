package com.br.patientAPI.dtos;

import com.br.patientAPI.models.Patient;

public record PatientData( String name, String cpf, String email) {

	public PatientData(Patient patient) {
		this(patient.getName(), patient.getCpf(), patient.getEmail());
	}

	@Override
	public String toString(){
		String string = 
		"[\n"+
		"\t{ \n"+
			"\t\t\"name\": \""+this.name+"\",\n"+
			"\t\t\"cpf\": \""+this.cpf+"\",\n"+
			"\t\t\"email\": \""+this.email+"\"\n"+
		"\t}\n"+
		"]";
		return string;
	}
}


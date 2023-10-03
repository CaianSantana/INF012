package ifba.edu.br.Clinica.dtos;

import ifba.edu.br.Clinica.models.Patient;

public record PatientData(Long id, String name, String cpf, Long IDaddress) {

	public PatientData(Patient patient) {
		this(patient.getId() ,patient.getName(), patient.getCPF(), patient.getAddress().getId());
	}
}


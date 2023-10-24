package com.br.doctorAPI.dtos;

import com.br.doctorAPI.enums.Specialty;
import com.br.doctorAPI.models.Doctor;

public record DoctorData(Long id, String name, String cpf, String Phone, Long IDaddress, String crm, Specialty specialty) {

	public DoctorData(Doctor doctor) {
		this(doctor.getId() ,doctor.getName(), doctor.getCpf(), doctor.getPhone(),doctor.getAddress().getId(), doctor.getCrm(), doctor.getSpecialty());
	}
}

package com.br.doctorAPI.dtos;

import com.br.doctorAPI.enums.Specialty;
import com.br.doctorAPI.models.Doctor;

public record DoctorData(String name, String email, String crm, Specialty specialty) {

	public DoctorData(Doctor doctor) {
		this( doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
	}
}

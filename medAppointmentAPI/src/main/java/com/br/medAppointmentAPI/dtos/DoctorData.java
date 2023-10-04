package com.br.medAppointmentAPI.dtos;

import com.br.medAppointmentAPI.enums.Specialty;
import com.br.medAppointmentAPI.models.Doctor;

public record DoctorData(Long id, String name, String cpf, String Phone, Long IDaddress, String crm, Specialty specialty) {

	public DoctorData(Doctor doctor) {
		this(doctor.getId() ,doctor.getName(), doctor.getCPF(), doctor.getPhone(),doctor.getAddress().getId(), doctor.getCRM(), doctor.getSpecialty());
	}
}

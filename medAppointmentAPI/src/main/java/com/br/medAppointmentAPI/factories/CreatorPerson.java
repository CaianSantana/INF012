package com.br.medAppointmentAPI.factories;

import com.br.medAppointmentAPI.AbstractProduts.Person;
import com.br.medAppointmentAPI.dtos.FormDoctor;
import com.br.medAppointmentAPI.dtos.FormPatient;
import com.br.medAppointmentAPI.enums.Status;
import com.br.medAppointmentAPI.models.Doctor;
import com.br.medAppointmentAPI.models.Patient;

public abstract class CreatorPerson {
	
	public Person createPerson(FormPatient data) {
		Patient person = (Patient) this.create();
		person.setName(data.name());
		person.setCPF(data.cpf());
		person.setEmail(data.email());
		person.setPhone(data.phone());
		person.setAddress(data.address());
		person.setStatus(Status.ACTIVE);
		return person;
	}
	public Doctor createPerson(FormDoctor data) {
		Doctor person = (Doctor) this.create();
		person.setName(data.name());
		person.setCPF(data.cpf());
		person.setEmail(data.email());
		person.setPhone(data.phone());
		person.setAddress(data.address());
		person.setCRM(data.crm());
		person.setSpecialty(data.specialty());
		person.setStatus(Status.ACTIVE);
		return person;
	}
	protected abstract Person create();
}

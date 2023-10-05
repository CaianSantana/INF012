package com.br.patientAPI.factories;

import com.br.patientAPI.AbstractProduts.Person;
import com.br.patientAPI.dtos.FormPatient;
import com.br.patientAPI.enums.Status;
import com.br.patientAPI.models.Patient;

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
	protected abstract Person create();
}

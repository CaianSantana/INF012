package com.br.doctorAPI.factories;

import com.br.doctorAPI.AbstractProduts.Person;
import com.br.doctorAPI.dtos.FormDoctor;
import com.br.doctorAPI.enums.Status;
import com.br.doctorAPI.models.Doctor;

public abstract class CreatorPerson {
	
	public Person createPerson(FormDoctor data) {
		Doctor person = (Doctor) this.create();
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

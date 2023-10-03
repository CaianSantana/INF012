package ifba.edu.br.Clinica.factories;

import ifba.edu.br.Clinica.AbstractProduts.Person;
import ifba.edu.br.Clinica.dtos.FormDoctor;
import ifba.edu.br.Clinica.dtos.FormPatient;
import ifba.edu.br.Clinica.enums.Occupation;
import ifba.edu.br.Clinica.enums.Specialty;
import ifba.edu.br.Clinica.models.Address;
import ifba.edu.br.Clinica.models.Doctor;
import ifba.edu.br.Clinica.models.Patient;

public abstract class CreatorPerson {
	
	public Person createPerson(FormPatient data) {
		Person person = this.create();
		person.setName(data.name());
		person.setCPF(data.cpf());
		person.setEmail(data.email());
		person.setAddress(data.address());
		return person;
	}
	public Person createPerson(FormDoctor data) {
		Person person = this.create();
		person.setName(data.name());
		person.setCPF(data.cpf());
		person.setEmail(data.email());
		person.setAddress(data.address());
		person.setCRM(data.crm());
		person.setSpecialty(data.specialty());
		return person;
	}
	public Person createPerson(String name, String cpf, String email, Address address, Occupation occupation) {
		Person person = this.create();
		person.setName(name);
		person.setCPF(cpf);
		person.setEmail(email);
		person.setAddress(address);
		return person;
}	
	protected abstract Person create();
}

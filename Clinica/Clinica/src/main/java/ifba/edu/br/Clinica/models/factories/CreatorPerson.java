package ifba.edu.br.Clinica.models.factories;

import ifba.edu.br.Clinica.dtos.FormDoctor;
import ifba.edu.br.Clinica.models.enums.Occupation;
import ifba.edu.br.Clinica.models.enums.Specialty;
import ifba.edu.br.Clinica.models.products.Address;
import ifba.edu.br.Clinica.models.products.Doctor;
import ifba.edu.br.Clinica.models.products.Patient;
import ifba.edu.br.Clinica.models.products.Person;

public class CreatorPerson {
	public Person createPerson(FormDoctor data) {
		Person person = this.create(data.occupation());
		person.setName(data.name());
		person.setCPF(data.cpf());
		person.setEmail(data.email());
		person.setAddress(data.address());
		person.setCRM(data.crm());
		person.setSpecialty(data.specialty());
		return person;
	}
	
	
//	public Person CreatorPerson(FormDoctor data) {
//		Person person = this.create(data.occupation());
//		person.setName(data.name());
//		person.setCPF(data.cpf());
//		person.setEmail(data.email());
//		person.setAddress(data.address());
//		person.setCRM(data.crm());
//		person.setSpecialty(data.specialty());
//		return person;
//	}
	public Person createPerson(String name, String cpf, String email, Address address, Occupation occupation) {
		Person person = this.create(occupation);
		person.setName(name);
		person.setCPF(cpf);
		person.setEmail(email);
		person.setAddress(address);
	return person;
}	

	protected Person create(Occupation occupation) {
		switch(occupation) {
			case DOCTOR : 
				return new Doctor();
			default :
				return new Patient();
		}
	};
}

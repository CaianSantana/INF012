package ifba.edu.br.Clinica.models;

import ifba.edu.br.Clinica.AbstractProduts.Person;
import ifba.edu.br.Clinica.enums.Occupation;
import ifba.edu.br.Clinica.enums.Specialty;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="Patients")
public class Patient implements Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Nonnull private String name;
	@Nonnull private String cpf;
	@Nonnull private String email;
	@ManyToOne(cascade = CascadeType.ALL)
	@Nonnull private Address address;

	public Patient() {
	}

	@Override
	public Long getId() {
		return id;
	}
	@Override
	public Occupation getOccupation(Occupation occupation) {
		return Occupation.PATIENT;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getCPF() {
		return cpf;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public Address getAddress() {
		// TODO Auto-generated method stub
		return address;
	}
	@Override
	public String getCRM() {
		return null;
	}
	@Override
	public Specialty getSpecialty() {
		return null;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	@Override
	public void setEmail(String email) {
		this.email = email;
		
	}

	@Override
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public void setCRM(String crm) {
	}

	@Override
	public void setSpecialty(Specialty specialty) {		
	}
	

}

package ifba.edu.br.Clinica.models;

import ifba.edu.br.Clinica.AbstractProduts.Person;
import ifba.edu.br.Clinica.enums.Occupation;
import ifba.edu.br.Clinica.enums.Specialty;
import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="Doctors")
public class Doctor implements Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 private String name;
	 private String cpf;
	 private String email;
	@ManyToOne(cascade = CascadeType.ALL)
	 private Address address;
	 private String crm;
	@Enumerated(EnumType.STRING)
	 private Specialty specialty;

	
	public Doctor() {
	}

	@Override
	public Long getId() {
		return this.id;
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getCPF() {
		return this.cpf;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public Address getAddress() {
		return address;
	}
	@Override
	public String getCRM() {
		return crm;
	}
	
	@Override
	public Occupation getOccupation(Occupation occupation) {
		return Occupation.DOCTOR;
	}
	@Override
	public Specialty getSpecialty() {
		return this.specialty;
	}
	@Override
	public void setId(Long id) {
		this.id=id;
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
		this.crm = crm;
	}
	
	@Override
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	
	
	
	
}

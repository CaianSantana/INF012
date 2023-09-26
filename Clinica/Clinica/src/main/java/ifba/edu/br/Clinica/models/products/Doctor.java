package ifba.edu.br.Clinica.models.products;

import ifba.edu.br.Clinica.models.enums.Occupation;
import ifba.edu.br.Clinica.models.enums.Specialty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="Doctor")
public class Doctor implements Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private String email;
	@ManyToOne
	private Address address;
	private String crm;
	@Enumerated(EnumType.STRING)
	private Specialty specialty;

	
	public Doctor() {
	}

	@Override
	public Long getId() {
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Address getAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getCRM() {
		return crm;
	}
	
	@Override
	public Occupation getOccupatio(Occupation occupation) {
		return Occupation.DOCTOR;
	}
	@Override
	public Specialty getSpecialty() {
		// TODO Auto-generated method stub
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
		this.crm = crm;
	}
	
	@Override
	public void setSpecialty(Specialty specialty) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

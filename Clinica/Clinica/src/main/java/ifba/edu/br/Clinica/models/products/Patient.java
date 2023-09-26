package ifba.edu.br.Clinica.models.products;

import ifba.edu.br.Clinica.models.enums.Occupation;
import ifba.edu.br.Clinica.models.enums.Specialty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="Patient")
public class Patient implements Person {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private String email;
	private Address address;

	public Patient() {
	}

	@Override
	public Long getId() {
		return id;
	}
	@Override
	public Occupation getOccupatio(Occupation occupation) {
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
		// TODO Auto-generated method stub

	}
	@Override
	public void setCPF(String cpf) {
		// TODO Auto-generated method stub

	}
	@Override
	public void setCRM(String crm) {
	}
	@Override
	public void setSpecialty(Specialty specialty) {
	}
	@Override
	public void setEmail(String email) {
		this.email = email;	
	}
	@Override
	public void setAddress(Address address) {
		this.address = address;
	}
	

}

package ifba.edu.br.Clinica.models.products;

import ifba.edu.br.Clinica.models.enums.Occupation;
import ifba.edu.br.Clinica.models.enums.Specialty;

public interface Person {
	//Getters
	public Long getId();
	public String getName();
	public String getCPF();
	public String getEmail();
	public Address getAddress();
	public Occupation getOccupatio(Occupation occupation);
	//Doctors Getters
	public String getCRM();
	public Specialty getSpecialty();
	
	
	//Setters
	public void setName(String name);
	public void setCPF(String cpf);
	public void setEmail(String email);
	public void setAddress(Address address);
	//Doctor Setters
	public void setCRM(String crm);
	public void setSpecialty(Specialty specialty);
	
	
}

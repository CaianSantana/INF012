package ifba.edu.br.Clinica.AbstractProduts;

import ifba.edu.br.Clinica.enums.Occupation;
import ifba.edu.br.Clinica.enums.Specialty;
import ifba.edu.br.Clinica.models.Address;

public interface Person {
	//Getters
	public Long getId();
	public String getName();
	public String getCPF();
	public String getEmail();
	public Address getAddress();
	public Occupation getOccupation(Occupation occupation);
	//Doctors Getters
	public String getCRM();
	public Specialty getSpecialty();
	
	
	//Setters
	public void setId(Long id);
	public void setName(String name);
	public void setCPF(String cpf);
	public void setEmail(String email);
	public void setAddress(Address address);
	//Doctor Setters
	public void setCRM(String crm);
	public void setSpecialty(Specialty specialty);
	
	
}

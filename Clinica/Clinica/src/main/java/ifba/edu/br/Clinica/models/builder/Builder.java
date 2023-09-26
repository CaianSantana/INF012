package ifba.edu.br.Clinica.models.builder;

import ifba.edu.br.Clinica.models.enums.Specialty;

public interface Builder {
	
	public void reset();
	public void withID(long id);
	public void withNameDoctor(String string);
	public void withNamePatient(String string);
	public void withCRM(String crm);
	public void withSpecialty(Specialty specialty);
	public void withCPF(String cpf);
	

}

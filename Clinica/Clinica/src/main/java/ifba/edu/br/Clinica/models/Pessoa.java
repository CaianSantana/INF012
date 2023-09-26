package ifba.edu.br.Clinica.models;

public interface Pessoa {
	
	public Long getId();
	public String getName();
	public String getCPF();
	public Occupation getOccupatio(Occupation occupation);
	public void setName(String name);
	public void setCPF(String cpf);
	
	
}

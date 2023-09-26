package ifba.edu.br.Clinica.models;

public class Doctor implements Pessoa {
	
	private Long id;
	private Occupation occupation = Occupation.Doctor;
	private String name;
	private String cpf;
	private String crm;
	private Specialty specialty;
	
	public Doctor() {
	}
	public Doctor(String name, String cpf, String crm, Specialty specialty) {
		this.name = name;
		this.cpf = cpf;
		this.crm = crm;
		this.specialty = specialty;
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

	public String getCrm() {
		return crm;
	}
	
	@Override
	public Occupation getOccupatio(Occupation occupation) {
		return occupation;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
}

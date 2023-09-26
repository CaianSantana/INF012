package ifba.edu.br.Clinica.dtos;


import ifba.edu.br.Clinica.models.enums.Specialty;
import ifba.edu.br.Clinica.models.products.Address;
import ifba.edu.br.Clinica.models.products.Doctor;


public record DoctorData(Long id, String name, String cpf, Address address, String crm, Specialty specialty) {

	public DoctorData(Doctor doctor) {
		this(doctor.getId() ,doctor.getName(), doctor.getCPF(), doctor.getAddress(), doctor.getCRM(), doctor.getSpecialty());
	}
}

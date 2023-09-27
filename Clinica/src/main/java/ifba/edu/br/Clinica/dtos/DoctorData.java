package ifba.edu.br.Clinica.dtos;


import ifba.edu.br.Clinica.enums.Specialty;
import ifba.edu.br.Clinica.models.Address;
import ifba.edu.br.Clinica.models.Doctor;


public record DoctorData(Long id, String name, String cpf, Long IDaddress, String crm, Specialty specialty) {

	public DoctorData(Doctor doctor) {
		this(doctor.getId() ,doctor.getName(), doctor.getCPF(), doctor.getAddress().getId(), doctor.getCRM(), doctor.getSpecialty());
	}
}

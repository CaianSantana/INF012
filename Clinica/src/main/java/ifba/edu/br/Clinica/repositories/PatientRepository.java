package ifba.edu.br.Clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.edu.br.Clinica.models.Doctor;
import ifba.edu.br.Clinica.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient>findByNameContaining(String name);
}

package ifba.edu.br.Clinica.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifba.edu.br.Clinica.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	List<Doctor>findByNameContaining(String name);
}

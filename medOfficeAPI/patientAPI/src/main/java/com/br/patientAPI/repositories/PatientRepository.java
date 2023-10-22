package com.br.patientAPI.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.patientAPI.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findById(Long id);
	List<Patient>findByNameContaining(String name);
}

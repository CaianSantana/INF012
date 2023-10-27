package com.br.patientAPI.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.patientAPI.enums.Status;
import com.br.patientAPI.models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	Optional<Patient> findById(Long id);
	Patient findByCpfContaining(String cpf);
	Page<Patient>findAllByStatus(Status status, Pageable pageable);
}

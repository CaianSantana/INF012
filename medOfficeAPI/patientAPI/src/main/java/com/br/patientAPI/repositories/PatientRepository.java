package com.br.patientAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.patientAPI.models.Patient;




public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient>findByNameContaining(String name);
}

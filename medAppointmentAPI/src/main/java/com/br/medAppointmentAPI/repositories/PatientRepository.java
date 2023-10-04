package com.br.medAppointmentAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.medAppointmentAPI.models.Patient;



public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient>findByNameContaining(String name);
}

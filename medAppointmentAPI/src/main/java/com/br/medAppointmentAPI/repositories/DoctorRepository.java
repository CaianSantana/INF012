package com.br.medAppointmentAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.medAppointmentAPI.models.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	List<Doctor>findByNameContaining(String name);
}

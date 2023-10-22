package com.br.doctorAPI.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.doctorAPI.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Optional<Doctor> findById(Long id);
	List<Doctor>findByNameContaining(String name);
}

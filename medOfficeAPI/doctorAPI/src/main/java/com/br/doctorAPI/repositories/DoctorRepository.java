package com.br.doctorAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.doctorAPI.models.Doctor;




public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	List<Doctor>findByNameContaining(String name);
}

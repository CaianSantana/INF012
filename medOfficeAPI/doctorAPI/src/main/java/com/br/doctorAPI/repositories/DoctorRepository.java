package com.br.doctorAPI.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.doctorAPI.enums.Status;
import com.br.doctorAPI.models.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	Page<Doctor> findAllByStatus(Pageable pageable, Status Status);
	Optional<Doctor> findById(Long id);
	Doctor findByCrmContaining(String crm);
}

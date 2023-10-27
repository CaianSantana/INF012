package com.br.medConsultAPI.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.medConsultAPI.model.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long>{
     Optional<Scheduling> findById(Long id);
}

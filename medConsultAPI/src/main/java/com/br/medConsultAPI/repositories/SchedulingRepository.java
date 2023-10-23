package com.br.medConsultAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.medConsultAPI.model.Scheduling;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long>{
}

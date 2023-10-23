package com.br.medConsultAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.medConsultAPI.model.Consult;

public interface ConsultRepository extends JpaRepository<Consult, Long>{

}

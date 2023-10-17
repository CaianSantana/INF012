package com.br.medConsultAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.medConsultAPI.model.Date;

public interface DateRepository extends JpaRepository<Long, Date>{

}

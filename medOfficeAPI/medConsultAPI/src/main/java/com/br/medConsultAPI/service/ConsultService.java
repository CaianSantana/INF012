package com.br.medConsultAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.br.medConsultAPI.model.Consult;
import com.br.medConsultAPI.repositories.ConsultRepository;

import dtos.ConsultData;
import dtos.FormConsult;

@Service
public class ConsultService {
	
	@Autowired
	private ConsultRepository repository;
	
	public List<ConsultData> converterLista(List<Consult> lista){
		return lista.stream().map(ConsultData::new).collect(Collectors.toList());
	}
	
	public List<ConsultData> listAll(){
		List<Consult> list = new ArrayList<Consult>();
		return this.converterLista(list);
	}
	
	public Consult register(FormConsult data) throws Exception {
		
		Consult consult = new Consult(data);
		repository.save(consult);
		return consult;
	}


	public void erase(Long id) {
		Consult consult = this.repository.getReferenceById(id);
		this.repository.delete(consult);
	}

	public void update(Long id, FormConsult data) {
			Consult consult = this.repository.getReferenceById(id);
			consult.setDoctor(data.doctor());
			consult.setPatient(data.patient());
			consult.setDate(data.date());
			this.repository.save(consult);
	}

}

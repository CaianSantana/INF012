package com.br.medConsultAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.medConsultAPI.clients.DoctorClient;
import com.br.medConsultAPI.clients.PatientClient;
import com.br.medConsultAPI.dtos.ConsultData;
import com.br.medConsultAPI.dtos.FormConsult;
import com.br.medConsultAPI.exceptions.DoctorNotFoundException;
import com.br.medConsultAPI.exceptions.InvalidDataException;
import com.br.medConsultAPI.exceptions.InvalidHourException;
import com.br.medConsultAPI.exceptions.PatientNotFoundException;
import com.br.medConsultAPI.model.Consult;
import com.br.medConsultAPI.repositories.ConsultRepository;



@Service
public class ConsultService {
	
	@Autowired
	private ConsultRepository repository;
	@Autowired
	private DoctorClient doctorClient;
	@Autowired
	private PatientClient patientClient;
	
	public List<ConsultData> converterLista(List<Consult> lista){
		return lista.stream().map(ConsultData::new).collect(Collectors.toList());
	}
	
	public List<ConsultData> listAll(){
		List<Consult> list = new ArrayList<Consult>();
		list.addAll(repository.findAll());
		return this.converterLista(list);
	}
	
	public Consult register(FormConsult data) throws DoctorNotFoundException, PatientNotFoundException, InvalidDataException, InvalidHourException {
		Consult consult = new Consult(data);
		doctorClient.findDoctorById(data.doctorID());
		patientClient.findPatientById(data.patientID());
		data.scheduling().dateValidation();
		data.scheduling().hourValidation();
		repository.save(consult);
		return consult;
	}


	public void erase(Long id) {
		Consult consult = this.repository.getReferenceById(id);
		this.repository.delete(consult);
	}

	public void update(Long id, FormConsult data) {
			Consult consult = this.repository.getReferenceById(id);
			consult.setDoctor(data.doctorID());
			consult.setPatient(data.patientID());
			consult.setDate(data.scheduling());
			this.repository.save(consult);
	}
}

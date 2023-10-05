package com.br.patientAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.patientAPI.dtos.FormPatient;
import com.br.patientAPI.dtos.PatientData;
import com.br.patientAPI.enums.Status;
import com.br.patientAPI.factories.CreatorPatient;
import com.br.patientAPI.factories.CreatorPerson;
import com.br.patientAPI.models.Patient;
import com.br.patientAPI.repositories.PatientRepository;





@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public List<PatientData> converterLista(List<Patient> lista){
		return lista.stream().map(PatientData::new).collect(Collectors.toList());
	}
	
	public List<PatientData> listAll(){
		List<Patient > list = new ArrayList<Patient >();
		for (Patient patient : this.patientRepository.findAll()) {
			if(patient.getStatus() == Status.ACTIVE) {
				list.add(patient);
			}
		}
		return  this.converterLista(list);
	}
	
	public Patient register(FormPatient data) throws Exception {
		CreatorPerson creator= new CreatorPatient();
		Patient patient = (Patient) creator.createPerson(data);
		if(patient.getName() == null||
				patient.getCPF() == null||
				patient.getEmail() == null ||
				patient.getPhone() == null) {
			throw new Exception();
		}
		patientRepository.save(patient);
		return patient;
	}

	public List<PatientData> findByName(String name) {
		return this.converterLista(this.patientRepository.findByNameContaining(name));
	}

	public void erase(Long id) {
		Patient patient = this.patientRepository.getReferenceById(id);
		patient.setStatus(Status.INACTIVE);;
		this.patientRepository.save(patient);
	}

	public void update(Long id, FormPatient data) {
		Patient patient = this.patientRepository.getReferenceById(id);
		patient.setName(data.name());
		patient.setCPF(data.cpf());
		patient.setEmail(data.email());
		patient.setAddress(data.address());
		this.patientRepository.save(patient);
	}

}

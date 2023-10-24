package com.br.patientAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.patientAPI.dtos.FormPatient;
import com.br.patientAPI.dtos.PatientData;
import com.br.patientAPI.enums.Status;
import com.br.patientAPI.exceptions.NullValueException;
import com.br.patientAPI.models.Patient;
import com.br.patientAPI.repositories.PatientRepository;


@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public List<PatientData> converterLista(List<Patient> lista){
		return lista.stream().map(PatientData::new).collect(Collectors.toList());
	}
	public Boolean verifyStatus(Patient patient) {
		if(patient.getStatus() == Status.ACTIVE) {
			return true;
		}
		return false;
	}
	
	public List<PatientData> listAll(){
		List<Patient > list = new ArrayList<Patient >();
		for (Patient patient : this.patientRepository.findAll()) {
			if(verifyStatus(patient)) {
				list.add(patient);
			}
		}
		return  this.converterLista(list);
	}
	
	public Patient register(FormPatient data) throws NullValueException {
		Patient patient = new Patient(data);
		if(patient.hasNull()) {
			throw new NullValueException();
		}
		patientRepository.save(patient);
		return patient;
	}

	public List<PatientData> findByName(String name) {
		List<Patient > list = new ArrayList<Patient >();
		for (Patient patient : this.patientRepository.findByNameContaining(name)) {
			if(verifyStatus(patient)) {
				list.add(patient);
			}
		}
		return this.converterLista(list);
	}
	
	public PatientData findById(Long id){
		if(!verifyStatus(this.patientRepository.findById(id).get())) {
			throw new NoSuchElementException();
		}
		return new PatientData(this.patientRepository.findById(id).orElseThrow());
	}

	public void erase(Long id) {
		Patient patient = this.patientRepository.getReferenceById(id);
		patient.setStatus(Status.INACTIVE);;
		this.patientRepository.save(patient);
	}

	public void update(Long id, FormPatient data) throws NullValueException {
		Patient patient = this.patientRepository.getReferenceById(id);
		patient.setName(data.name());
		patient.setCpf(data.cpf());
		patient.setEmail(data.email());
		patient.setAddress(data.address());
		if(patient.hasNull())
			throw new NullValueException();
		this.patientRepository.save(patient);
	}

}

package com.br.patientAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.patientAPI.dtos.FormPatient;
import com.br.patientAPI.dtos.PatientData;
import com.br.patientAPI.enums.Status;
import com.br.patientAPI.exceptions.NullValueException;
import com.br.patientAPI.exceptions.OperationNotAllowedException;
import com.br.patientAPI.models.Address;
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
	
	public List<PatientData> listAll(Pageable pageable) {
        List<Patient > list = new ArrayList<Patient >();
		for (Patient patient : this.patientRepository.findAllByStatus(Status.ACTIVE, pageable)) {
			list.add(patient);
		}
		return  this.converterLista(list);
    }

	public PatientData findByCpf(String cpf) throws NullValueException{
		Patient patient = this.patientRepository.findByCpfContaining(cpf);
		if(patient == null)
			throw new NullValueException("No patient with this cpf found.");
		return new PatientData(patient);
	}

	public PatientData findById(Long id)throws NoSuchElementException{
		if(!verifyStatus(this.patientRepository.findById(id).get())) {
			throw new NoSuchElementException("No patient with this ID found.");
		}
		return new PatientData(this.patientRepository.findById(id).orElseThrow());
	}

	
	public Patient register(FormPatient data) throws NullValueException {
		Patient patient = new Patient(data);
		if(patient.hasNull()) {
			throw new NullValueException("All fields, with the exception of the number and address complement, must be filled out.");
		}
		patientRepository.save(patient);
		return patient;
	}
	
	public void erase(Long id) {
		Patient patient = this.patientRepository.getReferenceById(id);
		patient.setStatus(Status.INACTIVE);;
		this.patientRepository.save(patient);
	}

	public Patient update(Long id, FormPatient data) throws NullValueException, OperationNotAllowedException {
		Patient patient = this.patientRepository.getReferenceById(id);
		patient.setName(data.name());
		patient.setAddress(new Address(data.address()));
		if(data.cpf()!=null
		&&data.email()!=null){
			if(!data.email().equals(patient.getEmail())
			||!data.cpf().equals(patient.getCpf()))
				throw new OperationNotAllowedException("It is not allowed to update CPF and email");
		}
		if(patient.hasNull())
			throw new NullValueException("All fields, with the exception of the number and address complement, must be filled out.");
		this.patientRepository.save(patient);
		return patient;
	}
    

}

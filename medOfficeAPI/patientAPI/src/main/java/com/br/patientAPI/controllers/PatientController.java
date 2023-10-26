package com.br.patientAPI.controllers;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.patientAPI.dtos.FormPatient;
import com.br.patientAPI.dtos.PatientData;
import com.br.patientAPI.exceptions.NullValueException;
import com.br.patientAPI.exceptions.OperationNotAllowedException;
import com.br.patientAPI.models.Patient;
import com.br.patientAPI.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/findAll/{pageNumber}")
	public Stream<PatientData> listAllPatients(@PathVariable int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("name"));
		Page<PatientData> page = new PageImpl<>(patientService.listAll(pageable), pageable, pageable.getPageSize());
		return page.get();
	}

	@GetMapping("/findByCpf")
	public ResponseEntity<String> findPatientByName(String cpf){
		String patient;
		try {
			patient = patientService.findByCpf(cpf).toString();
		} catch (NullValueException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<String>(patient, HttpStatus.ACCEPTED);
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<String> findById(@PathVariable Long id) {
		String patient;
		try {
			patient = patientService.findById(id).toString();
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		return  new ResponseEntity<String>(patient, HttpStatus.ACCEPTED);
	}
	
	@PostMapping
	public ResponseEntity<String> registerPatient(@RequestBody FormPatient data){
		Patient patient;
		try {
			patient = patientService.register(data);
		} catch (NullValueException e) {
			return new ResponseEntity<String>(e.getMessage() ,HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>(new PatientData(patient).toString() ,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String>updateDoctor(@PathVariable Long id, @RequestBody FormPatient data) {
		Patient patient;
		try {
			patient = patientService.update(id, data);
		} catch (NullValueException | OperationNotAllowedException e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<String>(new PatientData(patient).toString(), HttpStatus.ACCEPTED);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> erasePatient(@PathVariable Long id) {
		patientService.erase(id);
		return  new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
}

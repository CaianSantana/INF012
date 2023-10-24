package com.br.patientAPI.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.br.patientAPI.models.Patient;
import com.br.patientAPI.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public List<PatientData> listAllPatients(){
		return patientService.listAll();
	}

	@GetMapping("/findByName")
	public List<PatientData> findPatientByName(String name){
		return patientService.findByName(name);
	}

	@GetMapping("/findById/{id}")
	public PatientData findById(@PathVariable Long id) {
		PatientData patient = patientService.findById(id);
		return patient;
	}
	
	@PostMapping
	public ResponseEntity<PatientData> registerPatient(@RequestBody FormPatient data){
		Patient patient;
		try {
			patient = patientService.register(data);
		} catch (NullValueException e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<PatientData>( new PatientData(patient) ,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PatientData>updateDoctor(@PathVariable Long id, @RequestBody FormPatient data) {
		try {
			patientService.update(id, data);
		} catch (NullValueException e) {
			return new ResponseEntity<PatientData>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<PatientData>(HttpStatus.ACCEPTED);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> erasePatient(@PathVariable Long id) {
		patientService.erase(id);
		return  new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
}

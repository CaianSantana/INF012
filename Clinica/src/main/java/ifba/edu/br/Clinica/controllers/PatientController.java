package ifba.edu.br.Clinica.controllers;

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

import ifba.edu.br.Clinica.dtos.DoctorData;
import ifba.edu.br.Clinica.dtos.FormDoctor;
import ifba.edu.br.Clinica.dtos.FormPatient;
import ifba.edu.br.Clinica.dtos.PatientData;
import ifba.edu.br.Clinica.models.Doctor;
import ifba.edu.br.Clinica.models.Patient;
import ifba.edu.br.Clinica.service.DoctorService;
import ifba.edu.br.Clinica.service.PatientService;


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

	@PostMapping
	public ResponseEntity<PatientData> registerPatient(@RequestBody FormPatient data) {
		Patient patient= patientService.register(data);
		return new ResponseEntity<PatientData>( new PatientData(patient) ,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PatientData>updateDoctor(@PathVariable Long id, @RequestBody FormPatient data) {
		patientService.update(id, data);
		return new ResponseEntity<PatientData>(HttpStatus.ACCEPTED);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> erasePatient(@PathVariable Long id) {
		patientService.erase(id);
		return  new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
}

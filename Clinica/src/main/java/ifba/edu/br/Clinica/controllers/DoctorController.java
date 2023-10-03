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
@RequestMapping("/doctors")
public class DoctorController {

	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping
	public List<DoctorData> listAllDoctors(){
		return doctorService.listAll();
	}
	
	@GetMapping("/findByName")
	public List<DoctorData> findDoctorByName(String name){
		return doctorService.findByName(name);
	}

	@PostMapping
	public ResponseEntity<DoctorData> registerDoctor(@RequestBody FormDoctor data) {
		Doctor doctor= doctorService.register(data);
		return new ResponseEntity<DoctorData>( new DoctorData(doctor) ,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DoctorData>updateDoctor(@PathVariable Long id, @RequestBody FormDoctor data) {
		doctorService.update(id, data);
		return new ResponseEntity<DoctorData>(HttpStatus.ACCEPTED);
	} 

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eraseDoctor(@PathVariable Long id) {
		doctorService.erase(id);
		return  new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
}

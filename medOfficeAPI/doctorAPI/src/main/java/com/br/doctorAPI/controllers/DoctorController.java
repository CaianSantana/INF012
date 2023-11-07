package com.br.doctorAPI.controllers;

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
import com.br.doctorAPI.dtos.DoctorData;
import com.br.doctorAPI.dtos.FormDoctor;
import com.br.doctorAPI.exception.CrmAlreadyExistsException;
import com.br.doctorAPI.exception.NullValuesException;
import com.br.doctorAPI.exception.OperationNotAllowedException;
import com.br.doctorAPI.models.Doctor;
import com.br.doctorAPI.service.DoctorService;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping("/findAll/{pageNumber}")
	public Stream<DoctorData> listAllDoctors(@PathVariable int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("name"));
		Page<DoctorData> page = new PageImpl<>(doctorService.listAll(pageable), pageable, pageable.getPageSize());
		return page.get();
	}
	
	@GetMapping("/findByCrm")
	public DoctorData findDoctorBycrm(String crm) {
		return doctorService.findByCrm(crm);
	}
	@GetMapping("/findById/{id}")
	public DoctorData findDoctorById(@PathVariable Long id) {
		return doctorService.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<DoctorData> registerDoctor(@RequestBody FormDoctor data) throws CrmAlreadyExistsException, NullValuesException{
		Doctor doctor;
		try {
			doctor = doctorService.register(data);
		} catch (CrmAlreadyExistsException e) {
			throw new CrmAlreadyExistsException();
		} catch (NullValuesException e) {
			throw new NullValuesException();
		}
		return new ResponseEntity<DoctorData>(new DoctorData(doctor), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DoctorData>updateDoctor(@PathVariable Long id, @RequestBody FormDoctor data) throws NullValuesException, OperationNotAllowedException {
		try {
			doctorService.update(id, data);
		} catch (NullValuesException e) {
			throw new NullValuesException();
		} catch (OperationNotAllowedException e1){
			throw new OperationNotAllowedException();
		}
		return new ResponseEntity<DoctorData>(HttpStatus.ACCEPTED);
	} 

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eraseDoctor(@PathVariable Long id) {
		doctorService.erase(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	
}
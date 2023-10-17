package com.br.medConsultAPI.controllers;

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


import com.br.medConsultAPI.model.Consult;
import com.br.medConsultAPI.service.ConsultService;

import dtos.ConsultData;
import dtos.FormConsult;

@RestController
@RequestMapping("/Consults")
public class ConsultController {

	@Autowired
	private ConsultService service;
	
	@GetMapping
	public List<ConsultData> listAllConsults(){
		return service.listAll();
	}
	@PostMapping
	public ResponseEntity<ConsultData> scheduleConsult(@RequestBody FormConsult data) throws Exception{
		 
		Consult consult= service.register(data);
		return new ResponseEntity<ConsultData>( new ConsultData(consult) ,HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ConsultData> updateConsult(@PathVariable Long id,@RequestBody FormConsult data) throws Exception{
		 
		service.update(id,data);
		return new ResponseEntity<ConsultData>(HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteConsult(@PathVariable Long id){
		service.erase(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
}

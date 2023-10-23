package com.br.medConsultAPI.clients;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(url = "http://localhost:8082/doctor-ms/", name = "doctor-ms")
public interface DoctorClient {
	@RequestMapping(method = RequestMethod.GET, value = "/doctors/findById/{id}")
	public ResponseEntity<DoctorData> findDoctorById(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.GET, value = "doctors/findAll")
	public ResponseEntity<List<DoctorData>> findAllDoctors();
}
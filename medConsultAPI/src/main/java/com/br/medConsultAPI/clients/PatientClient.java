package com.br.medConsultAPI.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://localhost:8082/patient-ms/", name = "patient-ms")
public interface PatientClient {

	@RequestMapping(method = RequestMethod.GET, value = "/patients/findById/{id}")
	public ResponseEntity<PatientData> findPatientById(@PathVariable("id") Long id);
}

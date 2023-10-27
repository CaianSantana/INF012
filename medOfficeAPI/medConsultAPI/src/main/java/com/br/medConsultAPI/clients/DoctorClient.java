package com.br.medConsultAPI.clients;


import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(url = "http://localhost:8082/doctor-ms/", name = "doctor-ms")
public interface DoctorClient {
	@RequestMapping(method = RequestMethod.GET, value = "/doctors/findByCrm?crm={crm}")
	public DoctorData findDoctorByCrm(@PathVariable("crm") String crm);
	@RequestMapping(method = RequestMethod.GET, value = "doctors/findAll/{pageNumber}")
	public List<DoctorData> findAllDoctors(@PathVariable("pageNumber") int pageNumber);
}
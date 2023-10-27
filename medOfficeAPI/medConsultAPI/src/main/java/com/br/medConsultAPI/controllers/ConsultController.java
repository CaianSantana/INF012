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
import com.br.medConsultAPI.dtos.ConsultData;
import com.br.medConsultAPI.dtos.FormConsult;
import com.br.medConsultAPI.exceptions.CancelReasonCannotBeNullException;
import com.br.medConsultAPI.exceptions.DoctorCannotHaveMoreThanOneConsultAtTimeException;
import com.br.medConsultAPI.exceptions.DoctorNotFoundException;
import com.br.medConsultAPI.exceptions.InvalidDataException;
import com.br.medConsultAPI.exceptions.InvalidHourException;
import com.br.medConsultAPI.exceptions.InvalidSchedulingException;
import com.br.medConsultAPI.exceptions.MinimumThirtyMinuteNoticeException;
import com.br.medConsultAPI.exceptions.NoDoctorAvailableException;
import com.br.medConsultAPI.exceptions.PatientNotFoundException;
import com.br.medConsultAPI.exceptions.PatientOnlyHaveOneConsultPerDayException;
import com.br.medConsultAPI.model.Consult;
import com.br.medConsultAPI.service.ConsultService;

@RestController
@RequestMapping("/Consults")
public class ConsultController {
	@Autowired
	private ConsultService service;
	@GetMapping
	public List<ConsultData> listAllConsults(){
		return service.listAllConsults();
	}


	@PostMapping
	public ResponseEntity<ConsultData> scheduleConsult(@RequestBody FormConsult data) throws InvalidDataException, InvalidHourException, 
	PatientOnlyHaveOneConsultPerDayException, DoctorCannotHaveMoreThanOneConsultAtTimeException, NoDoctorAvailableException, 
	InvalidSchedulingException, MinimumThirtyMinuteNoticeException, PatientNotFoundException, DoctorNotFoundException{
		Consult consult;
		try {
			consult = service.register(data);
		} catch (DoctorNotFoundException e) {
			throw new DoctorNotFoundException();
		} catch (PatientNotFoundException e) {
			throw new PatientNotFoundException();
		} catch (InvalidDataException e) {
			throw new InvalidDataException();
		} catch(InvalidHourException e){
			throw new InvalidHourException();
		} catch(PatientOnlyHaveOneConsultPerDayException e){
			throw new PatientOnlyHaveOneConsultPerDayException();
		} catch(DoctorCannotHaveMoreThanOneConsultAtTimeException e){
			throw new DoctorCannotHaveMoreThanOneConsultAtTimeException();
		} catch(NoDoctorAvailableException e){
			throw new NoDoctorAvailableException();
		} catch(InvalidSchedulingException e){
			throw new InvalidSchedulingException();
		} catch(MinimumThirtyMinuteNoticeException e){
			throw new MinimumThirtyMinuteNoticeException();
		}
		return new ResponseEntity<ConsultData>(new ConsultData(consult), HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ConsultData> updateConsult(@PathVariable Long id, @RequestBody FormConsult data) throws Exception{
		service.update(id,data);
		return new ResponseEntity<ConsultData>(HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/cancel")
	public ResponseEntity<?> cancelConsult(@RequestBody Long id, @RequestBody String cancelReason){
		try {
			service.cancel(id, cancelReason);
		} catch (CancelReasonCannotBeNullException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
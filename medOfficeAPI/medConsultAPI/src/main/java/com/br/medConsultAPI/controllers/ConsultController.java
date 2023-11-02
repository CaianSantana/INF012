package com.br.medConsultAPI.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.br.medConsultAPI.exceptions.CannotScheduleToThePastException;
import com.br.medConsultAPI.exceptions.DoctorCannotHaveMoreThanOneConsultAtTimeException;
import com.br.medConsultAPI.exceptions.DoctorNotFoundException;
import com.br.medConsultAPI.exceptions.InvalidSchedulingException;
import com.br.medConsultAPI.exceptions.MinimumThirtyMinuteNoticeException;
import com.br.medConsultAPI.exceptions.MinimumTwentyFourHourNoticeException;
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
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@GetMapping
	public List<ConsultData> listAllConsults(){
		return service.listAllConsults();
	}

	@ExceptionHandler(ParseException.class)
	@PostMapping
	public ResponseEntity<ConsultData> scheduleConsult(@RequestBody FormConsult data) throws PatientOnlyHaveOneConsultPerDayException, 
	DoctorCannotHaveMoreThanOneConsultAtTimeException, NoDoctorAvailableException, InvalidSchedulingException,
	MinimumThirtyMinuteNoticeException, PatientNotFoundException, DoctorNotFoundException, ParseException, CannotScheduleToThePastException{
		Consult consult;
		consult = service.register(data);
		ConsultData consultData = new ConsultData(consult, service.findDoctorByCrm(consult.getCrm()), service.findPatientByCpf(consult.getCpf()));
		rabbitTemplate.convertAndSend("medConsultAPI.v1.consult-scheduled", null, consultData);
		return new ResponseEntity<ConsultData>(consultData, HttpStatus.CREATED);
	}
	@PutMapping("/{id}")
	public ResponseEntity<ConsultData> updateConsult(@PathVariable Long id, @RequestBody FormConsult data) throws Exception{
		service.update(id,data);
		return new ResponseEntity<ConsultData>(HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<?> cancelConsult(@PathVariable Long id, @RequestBody String cancelReason) throws MinimumTwentyFourHourNoticeException{
		try {
			service.cancel(id, cancelReason);
		} catch (CancelReasonCannotBeNullException e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
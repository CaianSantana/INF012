package com.br.medConsultAPI.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.medConsultAPI.clients.ConsultProxy;
import com.br.medConsultAPI.clients.DoctorData;
import com.br.medConsultAPI.dtos.ConsultData;
import com.br.medConsultAPI.dtos.FormConsult;
import com.br.medConsultAPI.enums.Status;
import com.br.medConsultAPI.exceptions.CancelReasonCannotBeNullException;
import com.br.medConsultAPI.exceptions.DoctorCannotHaveMoreThanOneConsultatAtTimeException;
import com.br.medConsultAPI.exceptions.DoctorNotFoundException;
import com.br.medConsultAPI.exceptions.InvalidDataException;
import com.br.medConsultAPI.exceptions.InvalidHourException;
import com.br.medConsultAPI.exceptions.NoDoctorAvailableException;
import com.br.medConsultAPI.exceptions.PatientNotFoundException;
import com.br.medConsultAPI.exceptions.PatientOnlyHaveOneConsultPerDayException;
import com.br.medConsultAPI.model.Consult;

@Service
public class ConsultService {
	
	@Autowired
	private ConsultProxy consultProxy;
	
	public boolean isNull(Object object) {
		if(object.equals(null))
			return true;
		return false;
	}
	public boolean isCancelled(Consult consult) {
		if(consult.getStatus() == Status.CANCELLED)
        	return true;
		return false;
    }
	private Long getRandomDoctor(Consult consult) throws NoDoctorAvailableException {
		List<DoctorData> docList = consultProxy.findAllDoctors();
		Collections.shuffle(docList);
		for(int i=0; i<docList.size(); i++) {
			for(Consult item: consultProxy.findAllConsults()) {
				if(item.getDoctorID() == docList.get(i).id()
						&&!item.getScheduling().compareDate(consult.getScheduling())
						&&!item.getScheduling().compareHour(consult.getScheduling())) {
						return docList.get(i).id();
					}
			}
		}
		throw new NoDoctorAvailableException();
	}
	
	
	
	public List<ConsultData> converterLista(List<Consult> lista){
		return lista.stream().map(ConsultData::new).collect(Collectors.toList());
	}
	public List<ConsultData> listAll(){
		List<Consult> list = new ArrayList<Consult>();
		list.addAll(consultProxy.findAllConsults());
		return this.converterLista(list);
	}
	public Consult register(FormConsult data) throws DoctorNotFoundException, PatientNotFoundException, InvalidDataException, InvalidHourException, 
	PatientOnlyHaveOneConsultPerDayException, DoctorCannotHaveMoreThanOneConsultatAtTimeException, NoDoctorAvailableException {
		Consult consult = new Consult(data);
		data.scheduling().dateValidation();
		data.scheduling().hourValidation();
		if(isNull(consultProxy.findDoctorById(data.doctorID()))) {
			consult.setDoctor(getRandomDoctor(consult));
			throw new DoctorNotFoundException();
		}
		if(isNull(consultProxy.findPatientById(data.patientID())))	
			throw new PatientNotFoundException();
		for(Consult item: this.consultProxy.findAllConsults()) {
			if(item.getPatientID() == consult.getPatientID()
					&&item.getScheduling().compareDate(consult.getScheduling())) {
				throw new PatientOnlyHaveOneConsultPerDayException();
			}
			if(item.getDoctorID() == consult.getDoctorID()
					&&item.getScheduling().compareDate(consult.getScheduling())
					&&item.getScheduling().compareHour(consult.getScheduling())) {
				throw new DoctorCannotHaveMoreThanOneConsultatAtTimeException();
			}
		}
		this.consultProxy.save(consult);
		return consult;
	}
	
	

	public void cancel(Long id, String cancelReason) throws CancelReasonCannotBeNullException {
		if(cancelReason.isBlank())
			throw new CancelReasonCannotBeNullException(); 
		Consult consult = this.consultProxy.findConsultById(id);
		consult.setStatus(Status.CANCELLED);
		consult.setCancelReason(cancelReason);
		this.consultProxy.save(consult);
	}
	public void update(Long id, FormConsult data) {
			Consult consult = this.consultProxy.findConsultById(id);
			consult.setDoctor(data.doctorID());
			consult.setPatient(data.patientID());
			consult.setDate(data.scheduling());
			this.consultProxy.save(consult);
	}
}
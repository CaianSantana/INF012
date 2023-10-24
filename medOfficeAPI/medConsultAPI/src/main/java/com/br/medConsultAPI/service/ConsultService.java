package com.br.medConsultAPI.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.br.medConsultAPI.clients.DoctorClient;
import com.br.medConsultAPI.clients.DoctorData;
import com.br.medConsultAPI.clients.PatientClient;
import com.br.medConsultAPI.clients.PatientData;
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
import com.br.medConsultAPI.repositories.ConsultRepository;

@Service
public class ConsultService {
	
	@Autowired
    DoctorClient doctorClient;

    @Autowired
    PatientClient patientClient;

    @Autowired
    ConsultRepository consultRepository;

	public Map<Long, DoctorData> doctorMap= new HashMap<Long, DoctorData>();;
    public Map<Long, PatientData> patientMap= new HashMap<Long, PatientData>();
    public Map<Long, Consult> consultMap= new HashMap<Long, Consult>();

	
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
	public boolean isCompleted(Consult consult){
		if(consult.getStatus() == Status.COMPLETED)
			return true;
		return false;
	}

	private Long getRandomDoctor(Consult consult) throws NoDoctorAvailableException {
		List<DoctorData> docList = this.findAllDoctors();
		Collections.shuffle(docList);
		for(int i=0; i<docList.size(); i++) {
			for(Consult item: this.consultRepository.findAll()) {
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

	public List<ConsultData> listAllConsults(){
		List<Consult> list = new ArrayList<Consult>();
		list.addAll(this.consultRepository.findAll());
		return this.converterLista(list);
	}
	public Consult findConsultById(Long id){
        Consult consult = consultMap.get(id);
        if(consult == null )
            consult = this.consultRepository.findById(id).get();           
        if(this.consultRepository.findById(id).isEmpty() || this.isCancelled(consult))
			throw new NoSuchElementException();
        consultMap.put(id, consult);
		return consult;
    }
	public List<DoctorData> findAllDoctors() {
        List<DoctorData> list = doctorClient.findAllDoctors();
        doctorMap.clear();
        for(DoctorData item: list)
            doctorMap.put(item.id(), item);
        return list;
    }
	public ResponseEntity<DoctorData> findDoctorById(Long id){
        DoctorData doctor = doctorMap.get(id);
        if(doctor == null){
            doctor = doctorClient.findDoctorById(id);
            doctorMap.put(id, doctor);
        } 
        return new ResponseEntity<DoctorData>(doctor, HttpStatus.ACCEPTED);
    }
	public ResponseEntity<PatientData> findPatientById(Long id){
        PatientData patient = patientMap.get(id);
        if(patient == null){
            patient = patientClient.findPatientById(id);
            patientMap.put(id, patient);
        }
        return new ResponseEntity<PatientData>(patient, HttpStatus.ACCEPTED);
    }

	public Consult register(FormConsult data) throws DoctorNotFoundException, PatientNotFoundException, InvalidDataException, InvalidHourException, 
	PatientOnlyHaveOneConsultPerDayException, DoctorCannotHaveMoreThanOneConsultatAtTimeException, NoDoctorAvailableException {
		Consult consult = new Consult(data);
		data.scheduling().dateValidation();
		data.scheduling().hourValidation();
		if(isNull(this.findDoctorById(data.doctorID()))) {
			consult.setDoctor(getRandomDoctor(consult));
			throw new DoctorNotFoundException();
		}
		if(isNull(this.findPatientById(data.patientID())))	
			throw new PatientNotFoundException();
		for(Consult item: this.consultRepository.findAll()) {
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
		this.consultRepository.save(consult);
		return consult;
	}
	
	

	public void cancel(Long id, String cancelReason) throws CancelReasonCannotBeNullException {
		if(cancelReason.isBlank())
			throw new CancelReasonCannotBeNullException(); 
		Consult consult = this.findConsultById(id);
		consult.setStatus(Status.CANCELLED);
		consult.setCancelReason(cancelReason);
		this.consultRepository.save(consult);
	}
	public void update(Long id, FormConsult data) {
			Consult consult = this.findConsultById(id);
			consult.setDoctor(data.doctorID());
			consult.setPatient(data.patientID());
			consult.setDate(data.scheduling());
			this.consultRepository.save(consult);
	}
}
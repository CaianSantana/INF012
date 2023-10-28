package com.br.medConsultAPI.service;

import java.text.ParseException;
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
import com.br.medConsultAPI.exceptions.DoctorCannotHaveMoreThanOneConsultAtTimeException;
import com.br.medConsultAPI.exceptions.DoctorNotFoundException;
import com.br.medConsultAPI.exceptions.InvalidSchedulingException;
import com.br.medConsultAPI.exceptions.MinimumThirtyMinuteNoticeException;
import com.br.medConsultAPI.exceptions.MinimumTwentyFourHourNoticeException;
import com.br.medConsultAPI.exceptions.NoDoctorAvailableException;
import com.br.medConsultAPI.exceptions.PatientNotFoundException;
import com.br.medConsultAPI.exceptions.PatientOnlyHaveOneConsultPerDayException;
import com.br.medConsultAPI.model.Consult;
import com.br.medConsultAPI.model.Scheduling;
import com.br.medConsultAPI.repositories.ConsultRepository;

@Service
public class ConsultService {
	
	@Autowired
    DoctorClient doctorClient;

    @Autowired
    PatientClient patientClient;

    @Autowired
    ConsultRepository consultRepository;

	public Map<String, DoctorData> doctorMap= new HashMap<String, DoctorData>();;
    public Map<String, PatientData> patientMap= new HashMap<String, PatientData>();
    public Map<Long, Consult> consultMap= new HashMap<Long, Consult>();

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

	private String getRandomDoctor(Consult consult) throws NoDoctorAvailableException {
		List<DoctorData> docList = this.findAllDoctors();
		List<Consult> consultList = this.consultRepository.findAll();
		Collections.shuffle(docList);
		for(int i=0; i<docList.size(); i++) {
			for(Consult item: consultList) {
				if(item.getCrm() == docList.get(i).crm()
						&&!item.getScheduling().compareDate(consult.getScheduling())||!item.getScheduling().compareAll(consult.getScheduling())) {
						return docList.get(i).crm();
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
        List<DoctorData> list = doctorClient.findAllDoctors(0);
        doctorMap.clear();
        for(DoctorData item: list)
            doctorMap.put(item.crm(), item);
        return list;
    }
	public ResponseEntity<DoctorData> findDoctorByCrm(String crm){
        DoctorData doctor = doctorMap.get(crm);
        if(doctor == null){
            doctor = doctorClient.findDoctorByCrm(crm);
            doctorMap.put(crm, doctor);
        } 
        return new ResponseEntity<DoctorData>(doctor, HttpStatus.ACCEPTED);
    }
	public ResponseEntity<PatientData> findPatientByCpf(String cpf){
        PatientData patient = patientMap.get(cpf);
        if(patient == null){
            patient = patientClient.findPatientByCpf(cpf);
            patientMap.put(cpf, patient);
        }
        return new ResponseEntity<PatientData>(patient, HttpStatus.ACCEPTED);
    }

	public Consult register(FormConsult data) throws DoctorNotFoundException, PatientNotFoundException,
	PatientOnlyHaveOneConsultPerDayException, DoctorCannotHaveMoreThanOneConsultAtTimeException, NoDoctorAvailableException, InvalidSchedulingException, MinimumThirtyMinuteNoticeException, ParseException {
		Consult consult = new Consult(data);
		consult.validateConsult(this.consultRepository.findAll());
		if(data.crm() ==null) {
			consult.setCrm((getRandomDoctor(consult)));
		}else if(this.findDoctorByCrm(data.crm()) == null)
			throw new DoctorNotFoundException();
		if(this.findPatientByCpf(data.cpf())==null)	
			throw new PatientNotFoundException();
		this.consultRepository.save(consult);
		return consult;
	}
	
	

	public void cancel(Long id, String cancelReason) throws CancelReasonCannotBeNullException, MinimumTwentyFourHourNoticeException {
		if(cancelReason.isBlank())
			throw new CancelReasonCannotBeNullException(); 
		Consult consult = this.findConsultById(id);
		consult.getScheduling().ValidateCancellation();
		consult.setStatus(Status.CANCELLED);
		consult.setCancelReason(cancelReason);
		this.consultRepository.save(consult);
	}
	public void update(Long id, FormConsult data) throws ParseException {
			Consult consult = this.findConsultById(id);
			Scheduling scheduling = new Scheduling(data.scheduling());
			consult.setCrm(data.crm());
			consult.setCpf(data.cpf());
			consult.setScheduling(scheduling);
			this.consultRepository.save(consult);
	}
}
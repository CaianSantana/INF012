package com.br.medConsultAPI.service;

import java.text.ParseException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.medConsultAPI.clients.DoctorClient;
import com.br.medConsultAPI.clients.DoctorData;
import com.br.medConsultAPI.clients.PatientClient;
import com.br.medConsultAPI.clients.PatientData;
import com.br.medConsultAPI.dtos.ConsultData;
import com.br.medConsultAPI.dtos.FormConsult;
import com.br.medConsultAPI.enums.Status;
import com.br.medConsultAPI.exceptions.CancelReasonCannotBeNullException;
import com.br.medConsultAPI.exceptions.CannotScheduleToThePastException;
import com.br.medConsultAPI.exceptions.DoctorCannotHaveMoreThanOneConsultAtTimeException;
import com.br.medConsultAPI.exceptions.InvalidSchedulingException;
import com.br.medConsultAPI.exceptions.MinimumThirtyMinuteNoticeException;
import com.br.medConsultAPI.exceptions.MinimumTwentyFourHourNoticeException;
import com.br.medConsultAPI.exceptions.NoDoctorAvailableException;
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


	private String getRandomDoctor(Consult consult) throws NoDoctorAvailableException {
		List<DoctorData> docList = this.findAllDoctors();
		List<Consult> consultList = this.consultRepository.findAll();
		Collections.shuffle(docList);
		for(int i=0; i<docList.size(); i++) {
			for(Consult item: consultList) {
				if(item.getCrm() == docList.get(i).crm()
						&&!item.getScheduling().compareAll(consult.getScheduling())) {
						return docList.get(i).crm();
					}
			}
		}
		throw new NoDoctorAvailableException();
	}
	
	public List<ConsultData> converterLista(List<Consult> list){
		List<ConsultData> listDTO = new ArrayList<ConsultData>();
		for(Consult item: list){
			listDTO.add(new ConsultData(item, findDoctorByCrm(item.getCrm()), findPatientByCpf(item.getCpf())));
		}
		return listDTO;
	}

	public List<ConsultData> listAllConsults(){
		List<Consult> list = new ArrayList<Consult>();
		for(Consult item: this.consultRepository.findAll())
			if(!item.isCancelled())
				list.add(item);
		return this.converterLista(list);
	}
	public Consult findConsultById(Long id){
        Consult consult = this.consultRepository.findById(id).orElseThrow(() ->new NoSuchElementException());
        if(consult.isCancelled())
			throw new NoSuchElementException();
		return consult;
    }
	public List<DoctorData> findAllDoctors() {
        List<DoctorData> list = doctorClient.findAllDoctors(0);
        return list;
    }
	public DoctorData findDoctorByCrm(String crm){
        DoctorData doctor = doctorClient.findDoctorByCrm(crm);
        return doctor;
    }
	public PatientData findPatientByCpf(String cpf){
        PatientData patient = patientClient.findPatientByCpf(cpf);
        return patient;
    }

	public Consult register(FormConsult data) throws PatientOnlyHaveOneConsultPerDayException, DoctorCannotHaveMoreThanOneConsultAtTimeException, NoDoctorAvailableException, 
	InvalidSchedulingException, MinimumThirtyMinuteNoticeException, ParseException, CannotScheduleToThePastException {
		Consult consult = new Consult(data);
		consult.validateConsult(this.consultRepository.findAll());
		if(data.crm() ==null) {
			consult.setCrm((getRandomDoctor(consult)));
		}else if(this.findDoctorByCrm(data.crm()) == null)
			throw new NoSuchElementException();
		if(this.findPatientByCpf(data.cpf())==null)	
			throw new NoSuchElementException();
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
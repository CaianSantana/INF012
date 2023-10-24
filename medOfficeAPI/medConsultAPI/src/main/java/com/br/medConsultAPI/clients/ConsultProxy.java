package com.br.medConsultAPI.clients;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.medConsultAPI.model.Consult;
import com.br.medConsultAPI.repositories.ConsultRepository;
import com.br.medConsultAPI.service.ConsultService;

@Service
public class ConsultProxy {
    
    @Autowired
    DoctorClient doctorClient;

    @Autowired
    PatientClient patientClient;

    @Autowired
    ConsultRepository consultRepository;

    ConsultService consultService;

    public Map<Long, DoctorData> doctorMap;
    public Map<Long, PatientData> patientMap;
    public Map<Long, Consult> consultMap;

    public ConsultProxy(){
        doctorMap = new HashMap<Long, DoctorData>();
        patientMap = new HashMap<Long, PatientData>();
        consultMap = new HashMap<Long, Consult>();
    }


    public List<Consult> findAllConsults() {
        return this.consultRepository.findAll();
    }
    public Consult findConsultById(Long id){
        Consult consult = consultMap.get(id);
        if(consult == null )
            consult = this.consultRepository.findById(id).get();           
        if(this.consultRepository.findById(id).isEmpty() || consultService.isCancelled(consult))
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

    public void save(Consult consult) {
        this.consultRepository.save(consult);
    }

}

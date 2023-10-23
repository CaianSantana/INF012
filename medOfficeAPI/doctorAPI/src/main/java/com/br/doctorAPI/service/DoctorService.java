package com.br.doctorAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.doctorAPI.dtos.DoctorData;
import com.br.doctorAPI.dtos.FormDoctor;
import com.br.doctorAPI.enums.Status;
import com.br.doctorAPI.exception.NullValuesException;
import com.br.doctorAPI.factories.CreatorDoctor;
import com.br.doctorAPI.factories.CreatorPerson;
import com.br.doctorAPI.models.Doctor;
import com.br.doctorAPI.repositories.DoctorRepository;




@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	public List<DoctorData> converterLista(List<Doctor> lista){
		return lista.stream().map(DoctorData::new).collect(Collectors.toList());
	}
	
	public Boolean verifyStatus(Doctor doctor) {
		if(doctor.getStatus() == Status.ACTIVE) {
			return true;
		}
		return false;
	}
	public List<DoctorData> listAll(){
		List<Doctor> list = new ArrayList<Doctor>();
		for (Doctor doctor : this.doctorRepository.findAll()) {
			if(verifyStatus(doctor)) {
				list.add(doctor);
			}
		}
		return this.converterLista(list);
	}
	
	public Doctor register(FormDoctor data) throws Exception {
		
		CreatorPerson creator= new CreatorDoctor();
		Doctor doctor = (Doctor) creator.createPerson(data);
		if(doctor.getName() == null||
				doctor.getCPF() == null||
				doctor.getEmail() == null ||
				doctor.getPhone() == null) {
			throw new Exception();
		}
		doctorRepository.save(doctor);
		return doctor;
	}

	public List<DoctorData> findByName(String name) {
		List<Doctor> list = new ArrayList<Doctor>();
		for (Doctor doctor : this.doctorRepository.findByNameContaining(name)) {
			if(verifyStatus(doctor)) {
				list.add(doctor);
			}
		}
		return this.converterLista(list);
	}
	
	public DoctorData findById(Long id) throws NoSuchElementException{
		if(!verifyStatus(this.doctorRepository.findById(id).get())) {
			throw new NoSuchElementException();
		}
		return new DoctorData(this.doctorRepository.findById(id).orElseThrow());
	}
	
	public void erase(Long id) {
		Doctor doctor = this.doctorRepository.getReferenceById(id);
		doctor.setStatus(Status.INACTIVE);;
		this.doctorRepository.save(doctor);
	}

	public void update(Long id, FormDoctor data) throws NullValuesException {
			Doctor doctor = this.doctorRepository.getReferenceById(id);
			if(doctor.hasNull())
				throw new NullValuesException();
			doctor.setName(data.name());
			doctor.setCPF(data.cpf());
			doctor.setEmail(data.email());
			doctor.setAddress(data.address());
			doctor.setCRM(data.crm());
			doctor.setSpecialty(data.specialty());
			this.doctorRepository.save(doctor);
	}

}

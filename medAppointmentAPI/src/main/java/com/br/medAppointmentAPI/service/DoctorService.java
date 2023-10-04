package com.br.medAppointmentAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.medAppointmentAPI.dtos.DoctorData;
import com.br.medAppointmentAPI.dtos.FormDoctor;
import com.br.medAppointmentAPI.enums.Status;
import com.br.medAppointmentAPI.models.Doctor;
import com.br.medAppointmentAPI.repositories.DoctorRepository;
import com.br.medAppointmentAPI.factories.CreatorPerson;
import com.br.medAppointmentAPI.factories.CreatorDoctor;


@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	public List<DoctorData> converterLista(List<Doctor> lista){
		return lista.stream().map(DoctorData::new).collect(Collectors.toList());
	}
	
	public List<DoctorData> listAll(){
		List<Doctor> list = new ArrayList<Doctor>();
		for (Doctor doctor : this.doctorRepository.findAll()) {
			if(doctor.getStatus() == Status.ACTIVE) {
				list.add(doctor);
			}
		}
		return  this.converterLista(list);
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
		return this.converterLista(this.doctorRepository.findByNameContaining(name));
	}

	public void erase(Long id) {
		Doctor doctor = this.doctorRepository.getReferenceById(id);
		doctor.setStatus(Status.INACTIVE);;
		this.doctorRepository.save(doctor);
	}

	public void update(Long id, FormDoctor data) {
			Doctor doctor = this.doctorRepository.getReferenceById(id);
			doctor.setName(data.name());
			doctor.setCPF(data.cpf());
			doctor.setEmail(data.email());
			doctor.setAddress(data.address());
			doctor.setCRM(data.crm());
			doctor.setSpecialty(data.specialty());
			this.doctorRepository.save(doctor);
	}

}

package com.br.doctorAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.doctorAPI.dtos.DoctorData;
import com.br.doctorAPI.dtos.FormDoctor;
import com.br.doctorAPI.enums.Status;
import com.br.doctorAPI.exception.CrmAlreadyExistsException;
import com.br.doctorAPI.exception.DoctorNotFoundException;
import com.br.doctorAPI.exception.NullValuesException;
import com.br.doctorAPI.exception.OperationNotAllowedException;
import com.br.doctorAPI.models.Address;
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
	public List<DoctorData> listAll(Pageable pageable){
		List<Doctor> list = new ArrayList<Doctor>();
		for (Doctor doctor :  this.doctorRepository.findAllByStatus(pageable, Status.ACTIVE)) {
			list.add(doctor);
		}
		return this.converterLista(list);
	}
	
	public Doctor register(FormDoctor data) throws NullValuesException, CrmAlreadyExistsException {
		Doctor doctor = new Doctor(data);
		if(doctor.hasNull())
			throw new NullValuesException();
		if(this.doctorRepository.findByCrmContaining(doctor.getCrm())!=null)
			throw new CrmAlreadyExistsException();
		doctorRepository.save(doctor);
		return doctor;
	}

	public DoctorData findByCrm(String crm) throws DoctorNotFoundException {
		Doctor doctor = this.doctorRepository.findByCrmContaining(crm);
		if(!verifyStatus(doctor))
			throw new DoctorNotFoundException();
		return new DoctorData(doctor);
	}
	
	public DoctorData findById(Long id) throws DoctorNotFoundException{
		if(!verifyStatus(this.doctorRepository.findById(id).get())) {
			throw new DoctorNotFoundException();
		}
		return new DoctorData(this.doctorRepository.findById(id).orElseThrow());
	}
	
	public void erase(Long id) {
		Doctor doctor = this.doctorRepository.getReferenceById(id);
		doctor.setStatus(Status.INACTIVE);;
		this.doctorRepository.save(doctor);
	}

	public void update(Long id, FormDoctor data) throws NullValuesException, OperationNotAllowedException {
			Doctor doctor = this.doctorRepository.getReferenceById(id);
			doctor.setName(data.name());
			doctor.setAddress(new Address(data.address()));
			doctor.setPhone(data.phone());
			if(data.email() != null
			&&data.crm() != null
			&&data.specialty() != null){
				if(!data.email().equals(doctor.getEmail())
				||(data.crm().equals(doctor.getCrm())
				||data.specialty() != doctor.getSpecialty()))
				throw new OperationNotAllowedException();
			}
			if(doctor.hasNull())
				throw new NullValuesException();
			this.doctorRepository.save(doctor);
	}

}

package ifba.edu.br.Clinica.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifba.edu.br.Clinica.repositories.DoctorRepository;
import ifba.edu.br.Clinica.dtos.DoctorData;
import ifba.edu.br.Clinica.dtos.FormDoctor;
import ifba.edu.br.Clinica.factories.CreatorDoctor;
import ifba.edu.br.Clinica.factories.CreatorPerson;
import ifba.edu.br.Clinica.models.Doctor;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	public List<DoctorData> converterLista(List<Doctor> lista){
		return lista.stream().map(DoctorData::new).collect(Collectors.toList());
	}
	
	public List<DoctorData> listAll(){
		return  this.converterLista(this.doctorRepository.findAll());
	}
	
	public Doctor register(FormDoctor data) {
		CreatorPerson creator= new CreatorDoctor();
		Doctor doctor = (Doctor) creator.createPerson(data);
		doctorRepository.save(doctor);
		return doctor;
	}

	public List<DoctorData> findByName(String name) {
		return this.converterLista(this.doctorRepository.findByNameContaining(name));
	}

	public void erase(Long id) {
		this.doctorRepository.deleteById(id);
	}

	public void update(Long id, FormDoctor data) {
			Doctor doctor = this.doctorRepository.getReferenceById(id);
			doctor.setId(id);
			doctor.setName(data.name());
			doctor.setCPF(data.cpf());
			doctor.setEmail(data.email());
			doctor.setAddress(data.address());
			doctor.setCRM(data.crm());
			doctor.setSpecialty(data.specialty());
			this.doctorRepository.save(doctor);
	}

}

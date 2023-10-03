package ifba.edu.br.Clinica.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifba.edu.br.Clinica.repositories.PatientRepository;
import ifba.edu.br.Clinica.dtos.PatientData;
import ifba.edu.br.Clinica.dtos.FormPatient;
import ifba.edu.br.Clinica.dtos.FormPatient;
import ifba.edu.br.Clinica.factories.CreatorPatient;
import ifba.edu.br.Clinica.factories.CreatorPerson;
import ifba.edu.br.Clinica.models.Patient;
import ifba.edu.br.Clinica.models.Patient;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	
	public List<PatientData> converterLista(List<Patient> lista){
		return lista.stream().map(PatientData::new).collect(Collectors.toList());
	}
	
	public List<PatientData> listAll(){
		return  this.converterLista(this.patientRepository.findAll());
	}
	
	public Patient register(FormPatient data) {
		CreatorPerson creator= new CreatorPatient();
		Patient patient = (Patient) creator.createPerson(data);
		patientRepository.save(patient);
		return patient;
	}

	public List<PatientData> findByName(String name) {
		return this.converterLista(this.patientRepository.findByNameContaining(name));
	}

	public void erase(Long id) {
		// TODO Auto-generated method stub
		this.patientRepository.deleteById(id);
	}

	public void update(Long id, FormPatient data) {
		Patient patient = this.patientRepository.getReferenceById(id);
		patient.setId(id);
		patient.setName(data.name());
		patient.setCPF(data.cpf());
		patient.setEmail(data.email());
		patient.setAddress(data.address());
		this.patientRepository.save(patient);
	}

}

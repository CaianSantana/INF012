package ifba.edu.br.Clinica.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ifba.edu.br.Clinica.repositories.DoctorRepository;
import ifba.edu.br.Clinica.dtos.DoctorData;
import ifba.edu.br.Clinica.dtos.FormDoctor;
import ifba.edu.br.Clinica.models.factories.CreatorPerson;
import ifba.edu.br.Clinica.models.products.Doctor;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;
	
	public List<DoctorData> converterLista(List<Doctor> lista){
		return lista.stream().map(DoctorData::new).collect(Collectors.toList());
	}
	
	public List<DoctorData> buscarTodos(){
		return  this.converterLista(this.doctorRepository.findAll());
	}
	
	public Doctor cadastrar(FormDoctor data) {
		CreatorPerson creator= new CreatorPerson();
		Doctor doctor = (Doctor) creator.createPerson(data);
		doctorRepository.save(doctor);
		return doctor;
	}

	public List<DoctorData> buscarPorNome(String nome) {
		
		return this.converterLista(this.doctorRepository.findByNameContaining(nome));
	}

	public void apagar(Long id) {
		// TODO Auto-generated method stub
		this.doctorRepository.deleteById(id);
	}

}

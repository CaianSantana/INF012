package ifba.edu.br.Clinica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifba.edu.br.Clinica.dtos.DoctorData;
import ifba.edu.br.Clinica.dtos.FormDoctor;
import ifba.edu.br.Clinica.models.products.Doctor;
import ifba.edu.br.Clinica.service.DoctorService;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

	
	@Autowired
	private DoctorService doctorService;
	
	@GetMapping
	public List<DoctorData> listarTodos(){
		return doctorService.buscarTodos();
	}
	
	@GetMapping("/buscarPorNome")
	public List<DoctorData> listarPorTitulo(String nome){
		return doctorService.buscarPorNome(nome);
	}
	
	@PostMapping
	public ResponseEntity<DoctorData> cadastrar(@RequestBody FormDoctor dados) {
		 Doctor doctor=doctorService.cadastrar(dados);
		return new ResponseEntity<DoctorData>( new DoctorData(doctor) ,HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> apagar(@PathVariable Long id) {
		doctorService.apagar(id);
		return  new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}

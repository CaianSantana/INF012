package edu.br.teste.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.br.teste.model.Usuario;
import edu.br.teste.repository.UserRepository;
import java.util.List;

//CONTROLLER SERVE PARA CONTROLAR REQUISIÇÕES HTTP
@RestController// USA PARA DIZER QUE É UM CONTROLLER DE REST(JSON).
public class UserController {
	
	@Autowired//INJETA DEPENDENCIA, NÃO SENDO NECESSÁRIO CRIAR CLASSE CONCRETA
	private UserRepository repository;

	@RequestMapping("/users")
	public List<Usuario> listar() {
		return repository.findAll();
	}
}
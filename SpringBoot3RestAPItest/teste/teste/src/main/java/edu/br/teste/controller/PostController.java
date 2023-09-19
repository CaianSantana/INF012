package edu.br.teste.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.br.teste.model.Post;
import edu.br.teste.repository.PostRepository;

//CONTROLLER SERVE PARA CONTROLAR REQUISIÇÕES HTTP
@RestController// USA PARA DIZER QUE É UM CONTROLLER DE REST(JSON).
public class PostController {
	
	@Autowired//INJETA DEPENDENCIA, NÃO SENDO NECESSÁRIO CRIAR CLASSE CONCRETA
	private PostRepository repository;

	@RequestMapping("/posts") //DIZ O QUE FAZER, CASO RECEBA REQUISIÇÕES NESSA URL
	public List<Post> listar() {
		return repository.findAll();
	}
	
	@PostMapping
	public void cadastrar(@RequestBody String dados) {
		System.out.println(dados);
	}
}



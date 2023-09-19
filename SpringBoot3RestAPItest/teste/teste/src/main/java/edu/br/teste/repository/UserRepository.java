package edu.br.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.br.teste.model.Usuario;

//ABSTRAIR INTERAÇÃO COM BD
public interface UserRepository extends JpaRepository<Usuario, Long> {

}

package edu.br.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.br.teste.model.Post;

//ABSTRAIR INTERAÇÃO COM BD
public interface PostRepository extends JpaRepository<Post, Long>{ 

}


package br.com.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.email.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{

}

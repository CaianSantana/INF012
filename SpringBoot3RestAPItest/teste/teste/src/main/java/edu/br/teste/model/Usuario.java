package edu.br.teste.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//CLASSE CRIADA PARA REPRESENTAR A TABELA USUARIO, É UMA ABSTRAÇÃO
//OBS: NÃO TEM GET DE SENHA, POIS ISSO É ERRADO

@Entity(name="usuarios")//SÓ PRA DIZER QUE É UMA TABELA, ELE FAZ MAPEAMENTO SZ
public class Usuario {
	@Id//ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)// GERA SOZINHO UMA IDENTIDADE PREVENDO CONCORRENCIA
	private Long id;
	private String nome;
	private String login;
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
}

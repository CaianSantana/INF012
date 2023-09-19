package edu.br.teste.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

//CLASSE CRIADA PARA REPRESENTAR A TABELA POSTS, É UMA ABSTRAÇÃO
//OBS: NÃO TEM GET DE USUARIO, POIS ISSO RETORNA DADOS SENSÍVEIS

@Entity(name="posts")//SÓ PRA DIZER QUE É UMA TABELA, ELE FAZ MAPEAMENTO SZ
public class Post {
	@Id //ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)// GERA SOZINHO UMA IDENTIDADE PREVENDO CONCORRENCIA
	private Long id;
    private String titulo;
    private String texto;
    @ManyToOne //RELACAO COM USUARIOS, OU SEJA, UM USUARIO TEM VÁRIOS POSTS E UM POST SÓ TEM UM USUARIO
    private Usuario usuario;
    @Enumerated(EnumType.STRING)//SÓ PRA DIZER QUE É UM TIPO DE CATEGORIA
    private Categoria categoria;
	
    public Long getId() {
		return id;
	}
    public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
    public String getTitulo() {
		return titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public Categoria getCategoria() {
		return categoria;
	}
}

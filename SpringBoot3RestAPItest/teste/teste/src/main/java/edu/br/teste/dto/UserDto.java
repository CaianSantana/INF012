//package edu.br.teste.dto;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import edu.br.teste.model.Usuario;
//
//public class UserDto {
//	private Long id;
//	private String nome;
//	private String login;
//	
//	public UserDto(Usuario usuario) {
//		this.nome = usuario.getNome();
//		this.login = usuario.getLogin();
//	}
//	
//	public static List<UserDto> converte(List<Usuario> lista){
//		return lista.stream().map(UserDto::new).collect(Collectors.toList());	
//	}
//}
package ifba.edu.br.Clinica.models.factories;

import ifba.edu.br.Clinica.models.Doctor;
import ifba.edu.br.Clinica.models.Occupation;
import ifba.edu.br.Clinica.models.Pessoa;
import ifba.edu.br.Clinica.models.Specialty;

public class CreatorPessoa {
	public Pessoa createDoctor(String name, String cpf, String crm, Specialty specialty, Occupation occupation) {
		Pessoa pessoa = this.create(occupation);
		pessoa.setName(name);
		pessoa.setCPF(cpf);
		return pessoa;
	}
	
//	public Pessoa createPOI(TipoPOI tipo, String nome, Double latitude, Double longitude, Integer estrelas) {
//		Pessoa poi = this.create(tipo);
//		poi.setNome(nome);
//		poi.setLatitude(latitude);
//		poi.setLongitude(longitude);
//		poi.avaliar(estrelas);
//		return poi;
//	}	

	protected Pessoa create(Occupation occupation) {
		switch(occupation) {
			case Doctor : 
				return new Doctor();
			default :
				return null;
		}
	};
}

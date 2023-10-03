package ifba.edu.br.Clinica.factories;

import ifba.edu.br.Clinica.AbstractProduts.Person;
import ifba.edu.br.Clinica.models.Doctor;

public class CreatorDoctor extends CreatorPerson {
	
	protected Person create(){
		return new Doctor();
	}
}

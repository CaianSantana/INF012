package ifba.edu.br.Clinica.factories;

import ifba.edu.br.Clinica.AbstractProduts.Person;
import ifba.edu.br.Clinica.models.Patient;

public class CreatorPatient extends CreatorPerson {

	protected Person create(){
		return new Patient();
	}

}

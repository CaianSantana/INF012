package com.br.patientAPI.factories;

import com.br.patientAPI.models.Patient;

public class CreatorPatient extends CreatorPerson {

	protected Patient create(){
		return new Patient();
	}

}

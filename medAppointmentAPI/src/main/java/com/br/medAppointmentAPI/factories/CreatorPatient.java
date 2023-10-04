package com.br.medAppointmentAPI.factories;

import com.br.medAppointmentAPI.models.Patient;

public class CreatorPatient extends CreatorPerson {

	protected Patient create(){
		return new Patient();
	}

}

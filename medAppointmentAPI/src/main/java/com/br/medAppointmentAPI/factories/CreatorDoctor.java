package com.br.medAppointmentAPI.factories;


import com.br.medAppointmentAPI.models.Doctor;

public class CreatorDoctor extends CreatorPerson {
	
	protected Doctor create(){
		return new Doctor();
	}
}

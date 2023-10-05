package com.br.doctorAPI.factories;

import com.br.doctorAPI.models.Doctor;

public class CreatorDoctor extends CreatorPerson {
	
	protected Doctor create(){
		return new Doctor();
	}
}

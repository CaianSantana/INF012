package com.br.doctorAPI.models;

import com.br.doctorAPI.dtos.FormDoctor;
import com.br.doctorAPI.enums.Specialty;
import com.br.doctorAPI.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Doctors")
public class Doctor implements HasNull {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	private String crm;
	@Enumerated(EnumType.STRING)
	private Specialty specialty;
	@Enumerated(EnumType.STRING)
	private Status status;

	public Doctor() {
	}
	public Doctor(FormDoctor data){
		this.name = data.name();
		this.email = data.email();
		this.phone = data.phone();
		this.address = data.address();
		this.crm = data.crm();
		this.specialty = data.specialty();
		this.status = Status.ACTIVE;
	}

	public boolean hasNull() {
		if(this.getName() == null
				||this.getEmail()== null
				||this.getCrm()== null
				||this.getSpecialty()== null
				||this.getAddress().hasNull())
			return true;
		return false;
	}
}
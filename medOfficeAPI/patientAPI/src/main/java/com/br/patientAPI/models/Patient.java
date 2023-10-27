package com.br.patientAPI.models;


import com.br.patientAPI.dtos.FormPatient;
import com.br.patientAPI.enums.Status;
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
@Entity(name="Patients")
public class Patient implements HasNull {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
	private String email;
	private String phone;
	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;
	@Enumerated(EnumType.STRING)
	private Status status;

	public Patient(){}
	public Patient(FormPatient data){
		this.name = data.name();
		this.cpf = data.cpf();
		this.email = data.email();
		this.phone = data.phone();
		this.address = new Address(data.address());
		this.status = Status.ACTIVE;
	}

	@Override
	public boolean hasNull() {
		if(this.getName()== null
				||this.getCpf()== null
				||this.getEmail()== null
				||this.getAddress().hasNull())
			return true;
		return false;
	}

	
}

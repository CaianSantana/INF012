package com.br.patientAPI.models;


import com.br.patientAPI.AbstractProduts.Person;
import com.br.patientAPI.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name="Patients")
public class Patient implements Person, HasNull {
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

	
	@Override
	public Long getId() {
		return id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getCPF() {
		return cpf;
	}
	@Override
	public String getEmail() {
		return email;
	}
	@Override
	public String getPhone() {
		return phone;
	}
	@Override
	public Address getAddress() {
		// TODO Auto-generated method stub
		return address;
	}
	public Status getStatus() {
		return status;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	@Override
	public void setEmail(String email) {
		this.email = email;
		
	}
	@Override
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public void setPhone(String phone) {
		this.phone = phone;		
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public boolean hasNull() {
		if(this.getName().isBlank()
				||this.getCPF().isBlank()
				||this.getEmail().isBlank()
				||this.getAddress().hasNull())
			return true;
		return false;
	}
}

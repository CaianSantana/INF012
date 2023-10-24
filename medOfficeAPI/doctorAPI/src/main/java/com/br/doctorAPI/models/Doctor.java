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


@Entity(name="Doctors")
public class Doctor implements HasNull {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String cpf;
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
		this.cpf = data.cpf();
		this.email = data.email();
		this.phone = data.phone();
		this.address = data.address();
		this.crm = data.crm();
		this.specialty = data.specialty();
		this.status = Status.ACTIVE;
	}

	
	public Long getId() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public String getCPF() {
		return this.cpf;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public Address getAddress() {
		return address;
	}
	public String getCRM() {
		return crm;
	}
	public Specialty getSpecialty() {
		return this.specialty;
	}
	public Status getStatus() {
		return status;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	public void setEmail(String email) {
		this.email = email;
		
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setCRM(String crm) {
		this.crm = crm;
	}
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}				
	public void setStatus(Status status) {
		this.status = status;
	}


	
	public boolean hasNull() {
		if(this.getName().isBlank()
				||this.getCPF().isBlank()
				||this.getEmail().isBlank()
				||this.getCRM().isBlank()
				||this.getSpecialty().equals(null)
				||this.getAddress().hasNull())
			return true;
		return false;
	}
}
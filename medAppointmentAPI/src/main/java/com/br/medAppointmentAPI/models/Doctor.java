package com.br.medAppointmentAPI.models;




import com.br.medAppointmentAPI.AbstractProduts.Person;
import com.br.medAppointmentAPI.enums.Specialty;
import com.br.medAppointmentAPI.enums.Status;
import com.br.medAppointmentAPI.models.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity(name="Doctors")
public class Doctor implements Person {
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

	@Override
	public Long getId() {
		return this.id;
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getCPF() {
		return this.cpf;
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
	public void setCRM(String crm) {
		this.crm = crm;
	}
	
	public void setSpecialty(Specialty specialty) {
		this.specialty = specialty;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}				
	public void setStatus(Status status) {
		this.status = status;
	}
}

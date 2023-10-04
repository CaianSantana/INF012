package com.br.medAppointmentAPI.models;


import com.br.medAppointmentAPI.AbstractProduts.Establishment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;


@Entity(name="Clinic")
public class Clinic implements Establishment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String corporateName;
	private String establishmentName;
	private String openingHours;
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	
	public Long getId() {
		return id;
	}
	public String getCorporateName() {
		return corporateName;
	}
	public String getEstablishmentName() {
		return establishmentName;
	}
	public String getOpeningHours() {
		return openingHours;
	}
	public String getEmail() {
		return email;
	}
	public Address getAddress() {
		return address;
	}
	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}
	public void setEstablishmentName(String establishmentName) {
		this.establishmentName = establishmentName;
	}
	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
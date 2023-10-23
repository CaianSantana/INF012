package com.br.doctorAPI.models;





import com.br.doctorAPI.dtos.FormAddress;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="Addresses")
public class Address implements HasNull{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	@Nonnull private String publicPlace;
	@Nonnull private Integer number;
	private String complement;
	@Nonnull private String neighborhood;
	@Nonnull private String city;
	@Nonnull private String state;
	@Nonnull private String zipCode;
	
	
	
	public Address(String publicPlace, Integer number, String complement, String neighborhood, String city,
			String state, String zipCode) {
		super();
		this.publicPlace = publicPlace;
		this.number = number;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	public Address() {
	}
	public Address(FormAddress data) {
		this.setPublicPlace(data.publicPlace());
		this.setNumber(data.number());
		this.setComplement(data.complement());
		this.setNeighborhood(data.neighborhood());
		this.setCity(data.city());
		this.setState(data.state());
		this.setZipCode(data.zipCode());
	}
	
	public long getId() {
		return id;
	}
	public String getPublicPlace() {
		return publicPlace;
	}
	public Integer getNumber() {
		return number;
	}
	public String getComplement() {
		return complement;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setComplement(String complement) {
		this.complement = complement;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public boolean findNull() {
		if(this.getPublicPlace() == null||
				this.getNeighborhood() == null||
				this.getCity() == null ||
				this.getState() == null ||
				this.getZipCode() == null) {
			return true;
		}
		return false;
	}
	public boolean hasNull() {
		if(this.getCity().isBlank()
				||this.getNeighborhood().isBlank()
				||this.getNumber().equals(null)
				||this.getPublicPlace().isBlank()
				||this.getState().isBlank()
				||this.getZipCode().isBlank())
			return true;
		return false;
	}
	
}
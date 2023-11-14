package com.br.doctorAPI.models;





import com.br.doctorAPI.dtos.FormAddress;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
	public boolean hasNull() {
		if(this.getCity()== null
				||this.getNeighborhood()== null
				||this.getNumber()== null
				||this.getPublicPlace()== null
				||this.getState()== null
				||this.getZipCode()== null)
			return true;
		return false;
	}
	
}
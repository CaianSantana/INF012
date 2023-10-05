package com.br.patientAPI.dtos;

import com.br.patientAPI.models.Address;

public record AddressData(Long id, String publicPlace, int number, String complement, String neighborhood, String city, String state, String zipCode) {

	public AddressData(Address address) {
		this(address.getId() ,address.getPublicPlace(), address.getNumber(), address.getComplement(), address.getCity(), address.getNeighborhood(), address.getState(), address.getZipCode());
	}
}

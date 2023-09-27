package ifba.edu.br.Clinica.factories;

import ifba.edu.br.Clinica.AbstractProduts.Person;
import ifba.edu.br.Clinica.dtos.FormAddress;

import ifba.edu.br.Clinica.models.Address;

public class CreatorAddress {
	
	public Address createAddress(String publicPlace, int number, String complement, String neighborhood, String city, String state, String zipCode) {
		Address address = this.create();
		address.setPublicPlace(publicPlace);
		address.setNumber(number);
		address.setComplement(complement);
		address.setNeighborhood(neighborhood);
		address.setCity(city);
		address.setState(state);
		address.setZipCode(zipCode);
		return address;
	}

	public Address createPerson(FormAddress data) {
		Address address = this.create();
		address.setPublicPlace(data.publicPlace());
		address.setNumber(data.number());
		address.setComplement(data.complement());
		address.setNeighborhood(data.neighborhood());
		address.setCity(data.city());
		address.setState(data.state());
		address.setZipCode(data.zipCode());
		return address;
	}
	private Address create() {
		return new Address();
	}
}

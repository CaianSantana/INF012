package ifba.edu.br.Clinica.models.factories;

import ifba.edu.br.Clinica.models.products.Address;

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

	private Address create() {
		return new Address();
	}
}

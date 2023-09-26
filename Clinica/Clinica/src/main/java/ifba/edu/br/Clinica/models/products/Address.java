package ifba.edu.br.Clinica.models.products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="Address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String PublicPlace;
	private int number;
	private String Complement;
	private String neighborhood;
	private String city;
	private String state;
	private String zipCode;
	
	
	public long getId() {
		return id;
	}
	public String getPublicPlace() {
		return PublicPlace;
	}
	public int getNumber() {
		return number;
	}
	public String getComplement() {
		return Complement;
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
		PublicPlace = publicPlace;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setComplement(String complement) {
		Complement = complement;
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

	
}

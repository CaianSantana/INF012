package ifba.edu.br.Clinica.models;


import ifba.edu.br.Clinica.dtos.FormAddress;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="Addresses")
public class Address{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	private String publicPlace;
	private int number;
	private String complement;
	private String neighborhood;
	private String city;
	private String state;
	private String zipCode;
	
	public Address() {};
	
	public Address(String publicPlace, int number, String complement, String neighborhood, String city, String state,
			String zipCode) {
		super();
		this.publicPlace = publicPlace;
		this.number = number;
		this.complement = complement;
		this.neighborhood = neighborhood;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
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
	public int getNumber() {
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
		publicPlace = publicPlace;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setComplement(String complement) {
		complement = complement;
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

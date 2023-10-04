package com.br.medAppointmentAPI.AbstractProduts;


import com.br.medAppointmentAPI.models.Address;

public interface Establishment {
	
	//Getters
	public Long getId();
	public String getCorporateName();
	public String getEstablishmentName();
	public String getOpeningHours();
	public String getEmail();
	public Address getAddress();
		
	//Setters
	public void setCorporateName(String corporateName);
	public void setEstablishmentName(String establishmentName);
	public void setOpeningHours(String openingHours); 
	public void setEmail(String email);
	public void setAddress(Address address);
		
}

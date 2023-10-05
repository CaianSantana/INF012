package com.br.patientAPI.AbstractProduts;


import com.br.patientAPI.enums.Status;
import com.br.patientAPI.models.Address;


public interface Person {
	//Getters
	public Long getId();
	public String getName();
	public String getCPF();
	public String getEmail();
	public String getPhone();
	public Address getAddress();
	public Status getStatus();
		
	//Setters
	public void setName(String name);
	public void setCPF(String cpf);
	public void setEmail(String email);
	public void setPhone(String Phone);
	public void setAddress(Address address);
	public void setStatus(Status status);
		
}

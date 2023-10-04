package com.br.medAppointmentAPI.dtos;

import com.br.medAppointmentAPI.enums.Specialty;
import com.br.medAppointmentAPI.enums.Status;
import com.br.medAppointmentAPI.models.Address;



public record  FormDoctor (Long id, String name, String cpf, String email, String phone, Address address, String crm, Specialty specialty, Status status)  {
}

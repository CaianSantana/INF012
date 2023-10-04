package com.br.medAppointmentAPI.dtos;

import com.br.medAppointmentAPI.enums.Status;
import com.br.medAppointmentAPI.models.Address;

public record FormPatient(Long id, String name, String cpf, String email, String phone, Address address, Status status) {
}

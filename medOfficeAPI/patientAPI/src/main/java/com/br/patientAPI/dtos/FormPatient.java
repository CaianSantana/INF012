package com.br.patientAPI.dtos;

import com.br.patientAPI.enums.Status;
import com.br.patientAPI.models.Address;

import jakarta.annotation.Nonnull;

public record FormPatient(Long id, String name, String cpf, String email, String phone, Address address, Status status) {
}

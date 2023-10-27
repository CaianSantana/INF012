package com.br.patientAPI.dtos;

import com.br.patientAPI.enums.Status;

public record FormPatient(Long id, String name, String cpf, String email, String phone, FormAddress address, Status status) {
}

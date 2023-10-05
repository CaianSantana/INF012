package com.br.doctorAPI.dtos;

import com.br.doctorAPI.enums.Specialty;
import com.br.doctorAPI.enums.Status;
import com.br.doctorAPI.models.Address;

public record  FormDoctor (Long id, String name, String cpf, String email, String phone, Address address, String crm, Specialty specialty, Status status)  {
}

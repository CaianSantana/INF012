package com.br.doctorAPI.dtos;

import com.br.doctorAPI.enums.Specialty;
import com.br.doctorAPI.enums.Status;

public record  FormDoctor (Long id, String name, String cpf, String email, String phone, FormAddress address, String crm, Specialty specialty, Status status)  {
}

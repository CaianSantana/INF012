package com.br.medConsultAPI.dtos;

import com.br.medConsultAPI.enums.Specialty;

public record FormConsult(Long id, String crm, String cpf, Specialty specialty, FormScheduling scheduling) {

}

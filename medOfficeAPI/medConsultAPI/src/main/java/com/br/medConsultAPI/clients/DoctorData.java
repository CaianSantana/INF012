package com.br.medConsultAPI.clients;

import com.br.medConsultAPI.enums.Specialty;

public record DoctorData(Long id, String name, String crm, String Phone, Specialty specialty) {

}

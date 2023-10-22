package com.br.medConsultAPI.dtos;


import com.br.medConsultAPI.enums.Specialty;
import com.br.medConsultAPI.model.Scheduling;


public record FormConsult(Long id, Long doctorID, Long patientID, Specialty specialty, Scheduling scheduling) {

}

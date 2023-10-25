package com.br.medConsultAPI.dtos;


import com.br.medConsultAPI.enums.Specialty;


public record FormConsult(Long id, Long doctorID, Long patientID, Specialty specialty, FormScheduling scheduling) {

}

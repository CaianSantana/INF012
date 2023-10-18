package com.br.medConsultAPI.dtos;


import com.br.medConsultAPI.model.Scheduling;

import enums.Specialty;


public record FormConsult(Long id, Long doctorID, Long patientID, Specialty specialty, Scheduling scheduling) {

}

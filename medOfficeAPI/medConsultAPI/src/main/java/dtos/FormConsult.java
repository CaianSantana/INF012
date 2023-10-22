package dtos;

import com.br.medConsultAPI.model.Scheduling;

public record FormConsult(Long id, Long IDdoctor, Long IDpatient, Scheduling scheduling) {

}

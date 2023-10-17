package dtos;

import com.br.medConsultAPI.model.Consult;
import com.br.medConsultAPI.model.Date;


public record ConsultData(Long id, Long IDDoctor, Long IDPatient, Date date) {

	public ConsultData(Consult consult) {
		this(consult.getId(), consult.getDoctor().id(), consult.getPatient().id(), consult.getDate());
	}
}

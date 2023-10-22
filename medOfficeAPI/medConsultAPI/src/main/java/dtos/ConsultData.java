package dtos;

import com.br.medConsultAPI.model.Consult;
import com.br.medConsultAPI.model.Scheduling;


public record ConsultData(Long id, Long IDDoctor, Long IDPatient, Scheduling scheduling) {

	public ConsultData(Consult consult) {
		this(consult.getId(), consult.getDoctorID(), consult.getPatientID(), consult.getScheduling());
	}
}

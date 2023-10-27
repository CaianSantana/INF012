package com.br.medConsultAPI.dtos;

import com.br.medConsultAPI.model.Consult;


public record ConsultData(Long id, String crm, String cpf, Integer day, Integer month, Integer year) {

	public ConsultData(Consult consult) {
		this(consult.getId(), consult.getCrm(), consult.getCpf(), consult.getScheduling().getDayDate(), consult.getScheduling().getMonthDate(), consult.getScheduling().getYearDate());
	}
}

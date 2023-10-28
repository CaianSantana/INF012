package com.br.medConsultAPI.dtos;


import com.br.medConsultAPI.model.Consult;


public record ConsultData(Long id, String crm, String cpf, String schedule) {
	public ConsultData(Consult consult) {
		this(consult.getId(), consult.getCrm(), consult.getCpf(), consult.getScheduling().getSchedule().toString());
	}
}

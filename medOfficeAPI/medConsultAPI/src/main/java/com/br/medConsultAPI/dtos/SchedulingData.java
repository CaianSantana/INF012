package com.br.medConsultAPI.dtos;

import java.util.Date;

import com.br.medConsultAPI.model.Scheduling;

public record SchedulingData(Long id, Date schedule) {
	public SchedulingData(Scheduling scheduling) {
		this(scheduling.getId(), scheduling.getSchedule());
	}
}

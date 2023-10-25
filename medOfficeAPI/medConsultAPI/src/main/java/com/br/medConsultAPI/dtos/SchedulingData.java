package com.br.medConsultAPI.dtos;

import com.br.medConsultAPI.enums.DayOfWeek;
import com.br.medConsultAPI.model.Scheduling;

public record SchedulingData(Long id, Integer day, Integer month, Integer year, DayOfWeek dayOfWeek, Integer hour, Integer minute) {
	public SchedulingData(Scheduling scheduling) {
		this(scheduling.getId(),scheduling.getDayDate(), scheduling.getMonthDate(), scheduling.getYearDate(), scheduling.getDayOfWeek(), scheduling.getHourTime(), scheduling.getMinuteTime());
	}
}

package dtos;

import com.br.medConsultAPI.enums.DayOfWeek;
import com.br.medConsultAPI.model.Date;

public record DateData(Long id, Integer day, Integer month, Integer year, DayOfWeek dayOfWeek, Integer hour, Integer minute) {
	public DateData(Date date) {
		this(date.getID(),date.getDay(), date.getMonth(), date.getYear(), date.getDayOfWeek(), date.getHour(), date.getMinute());
	}
}

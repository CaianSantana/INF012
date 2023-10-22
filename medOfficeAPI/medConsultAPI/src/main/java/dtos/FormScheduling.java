package dtos;

import com.br.medConsultAPI.enums.DayOfWeek;

public record FormScheduling(Long id, Integer day, Integer month, Integer year, DayOfWeek dayOfWeek, Integer hour, Integer minute) {
}

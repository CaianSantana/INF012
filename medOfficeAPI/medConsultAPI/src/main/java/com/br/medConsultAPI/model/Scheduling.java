package com.br.medConsultAPI.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.br.medConsultAPI.dtos.FormScheduling;
import com.br.medConsultAPI.exceptions.InvalidSchedulingException;
import com.br.medConsultAPI.exceptions.MinimumThirtyMinuteNoticeException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="Schedulings")
public class Scheduling {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date schedule;
	private final Integer DURATION = 1;
	private Integer hourFinal;
	
	public Scheduling() {
	}
	
	public Scheduling(FormScheduling data) throws ParseException {
		SimpleDateFormat sfd1 = new SimpleDateFormat("MM dd yyyy HH:mm");
		sfd1.setLenient(false);
		this.schedule = sfd1.parse(data.schedule());
		System.out.println(this.schedule);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(schedule);
		this.hourFinal = calendar.get(Calendar.HOUR)+DURATION;
	}
	
	public void validateScheduling() throws InvalidSchedulingException, MinimumThirtyMinuteNoticeException{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.schedule);
		Calendar currentCalendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);
		int currentMinute = currentCalendar.get(Calendar.MINUTE);
		if(hour<7 || hour>18
			|| calendar.get(Calendar.DAY_OF_WEEK) == 1
			|| calendar.get(Calendar.YEAR)<currentCalendar.get(Calendar.YEAR)
			|| calendar.get(Calendar.YEAR)>currentCalendar.get(Calendar.YEAR)+1)
			throw new InvalidSchedulingException();
		if((currentHour == hour && (minute - currentMinute)<30)
			||(currentHour==(hour-1) && (minute-currentMinute)>-30))
			throw new MinimumThirtyMinuteNoticeException();
	}
	public boolean compareDate(Scheduling scheduling) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.schedule);
		Calendar schedulingCalendar = Calendar.getInstance();
		schedulingCalendar.setTime(scheduling.getSchedule());
		if(calendar.get(Calendar.DAY_OF_YEAR) == schedulingCalendar.get(Calendar.DAY_OF_YEAR)
		&& calendar.get(Calendar.YEAR) == schedulingCalendar.get(Calendar.YEAR))
			return true;
		return false;
	}
	public boolean compareAll(Scheduling scheduling) {;
		if(this.schedule.compareTo(scheduling.getSchedule()) == 0)
			return true;
		return false;
	}
}
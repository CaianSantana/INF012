package com.br.medConsultAPI.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.br.medConsultAPI.dtos.FormScheduling;
import com.br.medConsultAPI.exceptions.InvalidSchedulingException;
import com.br.medConsultAPI.exceptions.MinimumThirtyMinuteNoticeException;
import com.br.medConsultAPI.exceptions.MinimumTwentyFourHourNoticeException;

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
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(schedule);
		this.hourFinal = calendar.get(Calendar.HOUR)+DURATION;
	}
	
	public Calendar getCalendar(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public void validateScheduling() throws InvalidSchedulingException, MinimumThirtyMinuteNoticeException{
		Calendar calendar = getCalendar(this.schedule);
		Calendar currentCalendar = getCalendar(new Date());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY);
		int currentMinute = currentCalendar.get(Calendar.MINUTE);
		if(hour<7 || hour>18
			|| calendar.get(Calendar.DAY_OF_WEEK) == 1
			|| calendar.get(Calendar.YEAR)<currentCalendar.get(Calendar.YEAR)
			|| calendar.get(Calendar.YEAR)>currentCalendar.get(Calendar.YEAR)+1)
			throw new InvalidSchedulingException();
		if(((schedule.compareTo(new Date())==0) && currentHour == hour && (minute - currentMinute)<30)
			||(currentHour==(hour-1) && ((60-currentMinute)+minute)<30))
			throw new MinimumThirtyMinuteNoticeException();
	}
	public void ValidateCancellation() throws MinimumTwentyFourHourNoticeException{
		Calendar calendar = getCalendar(this.schedule);
		Calendar currentCalendar = getCalendar(new Date());
		if(schedule.compareTo(new Date())==0
			||(schedule.compareTo(new Date())==1 && (currentCalendar.get(Calendar.HOUR_OF_DAY)-calendar.get(Calendar.HOUR_OF_DAY))<0))
			throw new MinimumTwentyFourHourNoticeException();
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
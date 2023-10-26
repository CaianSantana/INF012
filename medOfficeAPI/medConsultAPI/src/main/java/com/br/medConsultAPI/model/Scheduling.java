package com.br.medConsultAPI.model;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.br.medConsultAPI.dtos.FormScheduling;
import com.br.medConsultAPI.enums.DayOfWeek;
import com.br.medConsultAPI.exceptions.InvalidDataException;
import com.br.medConsultAPI.exceptions.InvalidHourException;
import com.br.medConsultAPI.exceptions.InvalidSchedulingException;
import com.br.medConsultAPI.exceptions.MinimumThirtyMinuteNoticeException;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private Integer dayDate;
	private Integer monthDate;
	private Integer yearDate;
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	private Integer hourTime;
	private Integer minuteTime;
	private final Integer DURATION = 1;
	private Integer hourFinal;
	
	public Scheduling() {
	}
	
	public Scheduling(FormScheduling data) {
		this.dayDate = data.day();
		this.monthDate = data.month();
		this.yearDate = data.year();
		this.dayOfWeek = data.dayOfWeek();
		this.hourTime = data.hour();
		this.hourFinal = data.hour()+DURATION;
		this.minuteTime = data.minute();
	}
	
	public void dateValidation() throws InvalidDataException {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer nextYear = calendar.get(Calendar.YEAR);
		nextYear++;
		Map<Integer, String> monthWith30Days = new HashMap<>();
		monthWith30Days.put(4, "April");
		monthWith30Days.put(6, "June");
		monthWith30Days.put(9, "September");
		monthWith30Days.put(11, "November");
		Map<Integer, String> monthWith31Days = new HashMap<>();
		monthWith31Days.put(1, "January");
		monthWith31Days.put(3, "March");
		monthWith31Days.put(5, "May");
		monthWith31Days.put(7, "July");
		monthWith31Days.put(8, "August");
		monthWith31Days.put(10, "October");
		monthWith31Days.put(12, "December");

		if(this.yearDate>=calendar.get(Calendar.YEAR) && this.yearDate<=nextYear){
			if(monthWith30Days.containsKey(this.monthDate)){
				if(this.dayDate<1 || this.dayDate>30){
					System.out.println("30");
					throw new InvalidDataException("Enter a valid day, month and year");
				}
				return;
			}	
			else if(monthWith31Days.containsKey(this.monthDate)){
				if(this.dayDate<1 || this.dayDate>31){
					System.out.println("31");
					throw new InvalidDataException("Enter a valid day, month and year");
				}
				return;
			}	
			else if(monthDate == 2){
				if((this.dayDate<1 || this.dayDate>28)){
					if(!(this.dayDate == 29 && Year.isLeap(this.yearDate))){
					System.out.println("29");
					throw new InvalidDataException("Enter a valid day, month and year");
					}
					System.out.println("28");
					return;
				}
			}
			else{
				System.out.println("ano "+ nextYear);
				throw new InvalidDataException("Enter a valid day, month and year");	
			}
		}
		throw new InvalidDataException("Enter a valid day, month and year");
	}
	public void hourValidation() throws InvalidHourException {
		if((this.hourTime<0 || this.hourTime>23)
			||(this.minuteTime<0 || this.minuteTime>59))
			throw new InvalidHourException("Enter a valid hour and minute");
	}
	public void consultTimeValidation() throws InvalidSchedulingException, MinimumThirtyMinuteNoticeException{
		Integer currentHour;
		Integer currentMinute;
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		currentHour = calendar.get(Calendar.HOUR_OF_DAY);
		currentMinute = calendar.get(Calendar.MINUTE);
		if(this.hourTime<7 
			|| this.hourTime>18
			|| this.dayOfWeek == DayOfWeek.SUNDAY)
			throw new InvalidSchedulingException("Consultations can only be scheduled from Monday to Saturday, between 7am and 6:59pm");
		if((currentHour==this.hourTime && (currentMinute-this.minuteTime)>=30)
			||(currentHour==(this.hourTime-1) && (this.minuteTime-currentMinute)<=-30))
			throw new MinimumThirtyMinuteNoticeException("Appointments can only be scheduled 30 minutes in advance");
	}
	public boolean compareDate(Scheduling scheduling) {
		if(this.monthDate.equals(scheduling.getMonthDate())
				&&this.dayDate.equals(scheduling.getDayDate()) 
				&&this.yearDate.equals(scheduling.getYearDate()))
			return true;
		return false;
	}
	public boolean compareTime(Scheduling scheduling) {
		if(scheduling.getHourTime().equals(this.hourTime) 
			|| (scheduling.getHourTime().equals(this.hourFinal) && scheduling.getMinuteTime()<this.minuteTime))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return dayOfWeek+" at "+hourTime+":"+minuteTime+", "+monthDate + "/" + dayDate + "/" + yearDate;
	}
}
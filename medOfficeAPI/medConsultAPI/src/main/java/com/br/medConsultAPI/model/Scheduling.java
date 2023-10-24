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
	private int dayDate;
	private int monthDate;
	private int yearDate;
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	private int hourTime;
	private int minuteTime;
	private final int DURATION = 1;
	private int hourFinal;
	
	public Scheduling() {
		hourFinal = this.hourTime+DURATION;
	}
	
	public Scheduling(FormScheduling data) {
		this.dayDate = data.day();
		this.monthDate = data.month();
		this.yearDate = data.year();
		this.dayOfWeek = data.dayOfWeek();
		this.hourTime = data.hour();
		this.hourFinal = this.hourTime+DURATION;
		this.minuteTime = data.minute();
	}
	
	public void dateValidation() throws InvalidDataException {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int nextYear = calendar.get(Calendar.YEAR);
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

		if(this.yearDate>=calendar.get(Calendar.YEAR) || this.yearDate<nextYear){
			if(monthWith30Days.containsKey(this.monthDate)){
				if(this.dayDate<1 || this.dayDate>30){
					System.out.println("30");
					throw new InvalidDataException();
				}
				return;
			}	
			else if(monthWith31Days.containsKey(this.monthDate)){
				if(this.dayDate<1 || this.dayDate>31){
					System.out.println("31");
					throw new InvalidDataException();
				}
				return;
			}	
			else if(monthDate == 2){
				if((this.dayDate<1 || this.dayDate>28)){
					if(!(this.dayDate == 29 && Year.isLeap(this.yearDate))){
					System.out.println("29");
					throw new InvalidDataException();
					}
					System.out.println("28");
					return;
				}
			}
			else{
				System.out.println("ano "+ nextYear);
				throw new InvalidDataException();	
			}
				
		}
	}
	public void hourValidation() throws InvalidHourException {
		if((this.hourTime<0 || this.hourTime>23)
			||(this.minuteTime<0 || this.minuteTime>59))
			throw new InvalidHourException();
	}
	public void consultTimeValidation() throws InvalidSchedulingException{
		if(this.hourTime<7 || this.hourTime>16)
			throw new InvalidSchedulingException();
	}
	public boolean compareDate(Scheduling scheduling) {
		if(this.monthDate == scheduling.getMonthDate()
				&&this.dayDate == scheduling.getDayDate()
				&&this.yearDate == scheduling.getYearDate())
			return true;
		return false;
	}
	public boolean compareTime(Scheduling scheduling) {
		if(scheduling.getHourTime() == this.hourTime
			|| (scheduling.getHourTime() == this.hourFinal && scheduling.getMinuteTime()<this.minuteTime))
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return dayOfWeek+" at"+hourTime+":"+minuteTime+", "+monthDate + "/" + dayDate + "/" + yearDate;
	}
}
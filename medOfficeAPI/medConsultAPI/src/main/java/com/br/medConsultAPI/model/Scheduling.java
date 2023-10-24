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
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
	
	public Scheduling() {}
	
	public Scheduling(FormScheduling data) {
		this.dayDate = data.day();
		this.monthDate = data.month();
		this.yearDate = data.year();
		this.dayOfWeek = data.dayOfWeek();
		this.hourTime = data.hour();
		this.minuteTime = data.minute();
	}
	
	public Long getID() {
		return id;
	}
	public Integer getDayDate() {
		return dayDate;
	}
	public Integer getMonth() {
		return monthDate;
	}
	public Integer getYear() {
		return yearDate;
	}
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	public Integer getHour() {
		return hourTime;
	}
	public Integer getMinute() {
		return minuteTime;
	}
	public void setDayDate(Integer dayDate) {
		this.dayDate = dayDate;
	}
	public void setMonth(Integer month) {
		this.monthDate = month;
	}
	public void setYear(Integer year) {
		this.yearDate = year;
	}
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public void setHour(Integer hour) {
		this.hourTime = hour;
	}
	public void setMinute(Integer minute) {
		this.minuteTime = minute;
	}
	public void dateValidation() throws InvalidDataException {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
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
		if(this.yearDate<1900 || this.yearDate>calendar.get(Calendar.YEAR)+1){
			if(monthWith30Days.containsKey(this.monthDate)){
				if(this.dayDate<1 || this.dayDate>30)
					throw new InvalidDataException();
				return;
			}	
			else if(monthWith31Days.containsKey(this.monthDate)){
				if(this.dayDate<1 || this.dayDate>31)
					throw new InvalidDataException();
				return;
			}	
			else if(monthDate == 2){
				if((this.dayDate<1 || this.dayDate>28))
					if(!(this.dayDate == 29 && Year.isLeap(this.yearDate)))
						throw new InvalidDataException();
				return;
			}
			else
				throw new InvalidDataException();	
		}
	}
	public void hourValidation() throws InvalidHourException {
		if((this.hourTime<0 || this.hourTime>23)
			||(this.minuteTime<0 || this.minuteTime>59))
			throw new InvalidHourException();
	}
	public boolean compareDate(Scheduling scheduling) {
		if(this.monthDate == scheduling.getMonth()
				&&this.dayDate == scheduling.getDayDate()
				&&this.yearDate == scheduling.getYear())
			return true;
		return false;
	}
	public boolean compareHour(Scheduling scheduling) {
		if(this.hourTime == scheduling.getHour()
				&&this.minuteTime == scheduling.getMinute())
			return true;
		return false;
	}
	
	@Override
	public String toString() {
		return dayOfWeek+" at"+hourTime+":"+minuteTime+", "+monthDate + "/" + dayDate + "/" + yearDate;
	}
}
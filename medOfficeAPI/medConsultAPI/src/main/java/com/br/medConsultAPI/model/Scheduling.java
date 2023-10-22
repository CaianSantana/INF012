package com.br.medConsultAPI.model;

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
	public void dateValidation() throws InvalidDataException {}
	public void hourValidation() throws InvalidHourException {}
	
	@Override
	public String toString() {
		return dayOfWeek+" at"+hourTime+":"+minuteTime+", "+monthDate + "/" + dayDate + "/" + yearDate;
	}
}

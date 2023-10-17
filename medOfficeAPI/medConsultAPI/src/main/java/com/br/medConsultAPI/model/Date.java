package com.br.medConsultAPI.model;

import com.br.medConsultAPI.enums.DayOfWeek;
import com.br.medConsultAPI.exceptions.InvalidDataException;
import com.br.medConsultAPI.exceptions.InvalidHourException;

import dtos.FormDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="dates")
public class Date {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer day;
	private Integer month;
	private Integer year;
	private DayOfWeek dayOfWeek;
	private Integer hour;
	private Integer minute;
	
	public Date(FormDate data) {
		this.day = data.day();
		this.month = data.month();
		this.year = data.year();
		this.dayOfWeek = data.dayOfWeek();
		this.hour = data.hour();
		this.minute = data.minute();
	}
	
	public Long getID() {
		return id;
	}
	public Integer getDay() {
		return day;
	}
	public Integer getMonth() {
		return month;
	}
	public Integer getYear() {
		return year;
	}
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	public Integer getHour() {
		return hour;
	}
	public Integer getMinute() {
		return minute;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	public void dateValidation(Integer day, Integer month, Integer year) throws InvalidDataException {}
	public void hourValidation(Integer hour, Integer minute) throws InvalidHourException {}
	
	@Override
	public String toString() {
		return dayOfWeek+" at"+hour+":"+minute+", "+month + "/" + day + "/" + year;
	}
}

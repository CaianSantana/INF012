package com.br.medConsultAPI.exceptions;

public class PatientOnlyHaveOneConsultPerDayException extends Exception {
    public PatientOnlyHaveOneConsultPerDayException(String message){
        super(message);
    }
}

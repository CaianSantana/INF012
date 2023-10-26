package com.br.medConsultAPI.exceptions;

public class DoctorAlreadyHaveScheduledAppointmentException extends Exception {

    public DoctorAlreadyHaveScheduledAppointmentException(String message){
        super(message);
    }
}

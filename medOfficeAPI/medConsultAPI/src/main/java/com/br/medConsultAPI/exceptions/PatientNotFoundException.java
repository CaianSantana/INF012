package com.br.medConsultAPI.exceptions;

public class PatientNotFoundException extends Exception {
    public PatientNotFoundException(String message){
        super(message);
    }
}

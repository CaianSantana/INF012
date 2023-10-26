package com.br.medConsultAPI.exceptions;

public class DoctorNotFoundException extends Exception {
    public DoctorNotFoundException(String message){
        super(message);
    }
}

package com.br.medConsultAPI.exceptions;

public class NoDoctorAvailableException extends Exception {
    public NoDoctorAvailableException(String message) {
        super(message);
    }
}

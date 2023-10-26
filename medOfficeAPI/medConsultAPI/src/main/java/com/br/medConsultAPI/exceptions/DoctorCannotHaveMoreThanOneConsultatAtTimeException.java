package com.br.medConsultAPI.exceptions;

public class DoctorCannotHaveMoreThanOneConsultatAtTimeException extends Exception {

    public DoctorCannotHaveMoreThanOneConsultatAtTimeException(String message){
        super(message);
    }
}

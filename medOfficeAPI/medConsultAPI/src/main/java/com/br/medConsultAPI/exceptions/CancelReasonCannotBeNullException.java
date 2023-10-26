package com.br.medConsultAPI.exceptions;

public class CancelReasonCannotBeNullException extends Exception {

    public CancelReasonCannotBeNullException(String message){
        super(message);
    }
}

package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Consultations can only be scheduled from Monday to Saturday, between 7am and 6:59pm")
public class InvalidSchedulingException extends Exception{
}

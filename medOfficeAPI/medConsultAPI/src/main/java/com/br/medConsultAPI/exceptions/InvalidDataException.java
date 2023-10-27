package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Enter a valid day, month and year")
public class InvalidDataException extends Exception {
}

package com.br.doctorAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="All fields, with the exception of the number and address complement, must be filled out.")
public class NullValuesException extends Exception {

}

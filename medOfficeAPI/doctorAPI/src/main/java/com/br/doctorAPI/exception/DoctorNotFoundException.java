package com.br.doctorAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Doctor not found.")
public class DoctorNotFoundException extends Exception {
    
}

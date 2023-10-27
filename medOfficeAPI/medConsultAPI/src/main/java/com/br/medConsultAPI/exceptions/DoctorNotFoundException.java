package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Unable to find the respective doctor in the system")
public class DoctorNotFoundException extends Exception {
}

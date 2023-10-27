package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="The doctor can only have one appointment at a time")
public class DoctorAlreadyHaveScheduledAppointmentException extends Exception {
}

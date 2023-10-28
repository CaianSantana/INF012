package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="The Patient cannot have more than one appointment per day")
public class PatientOnlyHaveOneConsultPerDayException extends Exception {
}

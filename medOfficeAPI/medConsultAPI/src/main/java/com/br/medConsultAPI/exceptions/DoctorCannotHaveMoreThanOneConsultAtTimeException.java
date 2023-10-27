package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Doctor Cannot Have More Than One Consult At Time Exception")
public class DoctorCannotHaveMoreThanOneConsultAtTimeException extends Exception {
}

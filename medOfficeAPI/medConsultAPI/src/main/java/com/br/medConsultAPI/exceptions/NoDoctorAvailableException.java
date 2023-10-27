package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No Doctor available in this moment. Try agayn later...")
public class NoDoctorAvailableException extends Exception {
}

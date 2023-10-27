package com.br.patientAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_MODIFIED, reason="It is not allowed to update CPF or email.")
public class OperationNotAllowedException extends Exception {
}

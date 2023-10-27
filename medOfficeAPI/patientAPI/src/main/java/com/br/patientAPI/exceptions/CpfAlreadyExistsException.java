package com.br.patientAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_MODIFIED, reason="This CPF is already registered in the system.")
public class CpfAlreadyExistsException extends Exception{
}

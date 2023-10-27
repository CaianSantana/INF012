package com.br.doctorAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_MODIFIED, reason="This CRM is already registered in the system.")
public class CrmAlreadyExistsException extends Exception {
    
}

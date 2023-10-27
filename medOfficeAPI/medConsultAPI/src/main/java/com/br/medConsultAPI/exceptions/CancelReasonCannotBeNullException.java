package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_MODIFIED, reason="You must enter a reason to cancel the scheduling.")
public class CancelReasonCannotBeNullException extends Exception {
}

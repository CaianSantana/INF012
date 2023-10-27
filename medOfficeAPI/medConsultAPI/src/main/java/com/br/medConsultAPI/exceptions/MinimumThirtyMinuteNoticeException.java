package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Appointments can only be scheduled 30 minutes in advance")
public class MinimumThirtyMinuteNoticeException extends Exception{
}

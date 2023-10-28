package com.br.medConsultAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE, reason="Appointments can only be canceled 24 hours in advance")
public class MinimumTwentyFourHourNoticeException extends Exception{

}

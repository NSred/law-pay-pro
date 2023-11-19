package com.lawagency.lawly.handler;

import com.lawagency.lawly.handler.exceptions.HttpClientException;
import com.lawagency.lawly.handler.exceptions.ProccessPaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ProccessPaymentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMessageNullException(ProccessPaymentException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Error while processing payment in Lawly");
        return errorResponse;
    }

    @ExceptionHandler({HttpClientException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMessageNullException(HttpClientException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("Error with trying to communicate with PSP using http client");
        return errorResponse;
    }

}

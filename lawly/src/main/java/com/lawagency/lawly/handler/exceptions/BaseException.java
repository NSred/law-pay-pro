package com.lawagency.lawly.handler.exceptions;

public abstract class BaseException extends RuntimeException {
    protected BaseException(final String message){
        super(message);
    }

}

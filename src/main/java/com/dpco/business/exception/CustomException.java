package com.dpco.business.exception;


import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private String message;

    private HttpStatus status;

    public CustomException(String message , HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

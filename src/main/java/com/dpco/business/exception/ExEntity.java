package com.dpco.business.exception;


public class ExEntity {

    private String message;
    private Integer httpStatus;

    public ExEntity(String message, Integer httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }
}

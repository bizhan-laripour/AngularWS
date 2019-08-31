package com.dpco.business.exception;

public class ResultBody {

    private Object result;

    private int status;

    public ResultBody(Object result, int status) {
        this.result = result;
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

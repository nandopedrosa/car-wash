package br.com.ccseapps.carwash.util;

public class Validation {

    private String message;
    private Status status;
    private Object payload;

    public Validation(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public Validation(String message, Status status, Object payload) {
        this.message = message;
        this.status = status;
        this.payload = payload;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}

package com.backend.murasaki.exceptions;

public class HttpErrorException extends RuntimeException {

    private int status;

    public HttpErrorException(String message, int status){
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
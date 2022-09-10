package com.backend.murasaki.exceptions;

public class NotFoundException extends HttpErrorException {

    public NotFoundException(String message){
        super(message, 404);
    }

}
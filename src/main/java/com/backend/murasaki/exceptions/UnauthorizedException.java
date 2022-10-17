package com.backend.murasaki.exceptions;

public class UnauthorizedException extends HttpErrorException {

    public UnauthorizedException(String message){
        super(message, 401);
    }
}

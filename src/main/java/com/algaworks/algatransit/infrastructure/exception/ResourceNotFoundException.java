package com.algaworks.algatransit.infrastructure.exception;

public class ResourceNotFoundException extends BaseException {
    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, String...args){
        super(message, args);
    }
}
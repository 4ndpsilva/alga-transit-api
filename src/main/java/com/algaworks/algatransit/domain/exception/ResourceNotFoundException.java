package com.algaworks.algatransit.domain.exception;

public class ResourceNotFoundException extends BaseException{
    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, String...args){
        super(message, args);
    }
}
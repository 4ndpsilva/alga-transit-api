package com.algaworks.algatransit.domain.exception;


public class AlreadyExistsException extends BaseException{
    public AlreadyExistsException(String message){
        super(message);
    }

    public AlreadyExistsException(String message, String...args){
        super(message, args);
    }
}
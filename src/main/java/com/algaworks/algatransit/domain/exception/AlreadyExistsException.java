package com.algaworks.algatransit.domain.exception;


import com.algaworks.algatransit.infrastructure.exception.BaseException;

public class AlreadyExistsException extends BaseException {
    public AlreadyExistsException(String message){
        super(message);
    }

    public AlreadyExistsException(String message, String...args){
        super(message, args);
    }
}
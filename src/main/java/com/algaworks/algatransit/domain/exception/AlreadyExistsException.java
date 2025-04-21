package com.algaworks.algatransit.domain.exception;

import lombok.Getter;

@Getter
public class AlreadyExistsException extends RuntimeException{
    private String[] args;

    public AlreadyExistsException(String message){
        super(message);
    }

    public AlreadyExistsException(String message, String...args){
        this(message);
        this.args = args;
    }
}
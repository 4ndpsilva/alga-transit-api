package com.algaworks.algatransit.infrastructure.exception;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException{
    private String[] args;

    public BaseException(String message){
        super(message);
    }

    public BaseException(String message, String...args){
        this(message);
        this.args = args;
    }
}
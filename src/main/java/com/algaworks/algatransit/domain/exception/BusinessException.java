package com.algaworks.algatransit.domain.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{
    private String[] args;

    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, String...args){
        this(message);
        this.args = args;
    }
}
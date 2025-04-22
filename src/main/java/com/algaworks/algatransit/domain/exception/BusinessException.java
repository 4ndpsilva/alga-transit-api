package com.algaworks.algatransit.domain.exception;

public class BusinessException extends BaseException{
    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, String...args){
        super(message, args);
    }
}
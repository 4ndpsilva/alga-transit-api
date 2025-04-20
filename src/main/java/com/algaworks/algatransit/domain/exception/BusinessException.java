package com.algaworks.algatransit.domain.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
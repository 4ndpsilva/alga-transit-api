package com.algaworks.algatransit.domain.exception;

import com.algaworks.algatransit.infrastructure.exception.BaseException;

public class BusinessException extends BaseException {
    public BusinessException(String message){
        super(message);
    }

    public BusinessException(String message, String...args){
        super(message, args);
    }
}
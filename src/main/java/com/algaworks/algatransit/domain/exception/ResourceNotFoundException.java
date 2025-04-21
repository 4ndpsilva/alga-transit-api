package com.algaworks.algatransit.domain.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private String[] args;

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(String message, String...args){
        this(message);
        this.args = args;
    }
}
package com.bookStore.bookStore.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class customException extends  RuntimeException{
    private static final long serialVersionUID = 1L;
    public customException(String message){
        super(message);
    }
    public customException(String message, Throwable throwable){
        super(message,throwable);
    }
}
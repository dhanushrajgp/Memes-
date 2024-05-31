package com.crio.starter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class MemeAlreadyExistException extends RuntimeException{

    public MemeAlreadyExistException(String message){
        super(message);
    }
    
}

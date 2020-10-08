package com.saraiev.adboard.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {
    
    @Getter
    protected HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    
    public BaseException(String s) {
        super(s);
    }

    
}

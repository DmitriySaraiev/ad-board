package com.saraiev.adboard.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {

    @Getter
    protected HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public UnauthorizedException(String s) {
        super(s);
    }
}

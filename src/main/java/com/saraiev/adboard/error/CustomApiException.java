package com.saraiev.adboard.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class CustomApiException extends BaseException {

    @Getter
    protected HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public CustomApiException(String s) {
        super(s);
    }
}

package com.saraiev.adboard.error.handler;

import com.saraiev.adboard.error.BaseException;
import com.saraiev.adboard.payload.DataApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleException(BaseException ex) {
        ex.printStackTrace();
        HttpStatus httpStatus = ex.getHttpStatus();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(new DataApiResponse<>(httpStatus.value(), "failure", errorResponse), httpStatus);
    }

}

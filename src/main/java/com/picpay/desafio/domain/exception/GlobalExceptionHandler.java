package com.picpay.desafio.domain.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseApplicationException.class)
    public ProblemDetail handleBaseApplicationException(BaseApplicationException exception){
        return exception.problemDetail();
    }
}

package com.picpay.desafio.domain.controller;

import com.picpay.desafio.domain.exception.BaseApplicationException;
import com.picpay.desafio.domain.exception.TransferSenderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BaseApplicationException.class)
    public ProblemDetail handleBaseApplicationException(BaseApplicationException e) {
        return e.problemDetail();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<InvalidParam> fieldErros = e.getFieldErrors()
            .stream()
            .map(f -> new InvalidParam(f.getField(), f.getDefaultMessage()))
            .toList();

        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Your request parameters didn't validate.");
        pb.setProperty("invalid-params", fieldErros);

        return pb;
    }

    @ExceptionHandler(TransferSenderNotFoundException.class)
    public ProblemDetail handleTransferSenderNotFound(TransferSenderNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Transfer sender not found");
        problemDetail.setDetail(e.getMessage());
        return problemDetail;
    }

    private record InvalidParam(String name, String reason) {}
}

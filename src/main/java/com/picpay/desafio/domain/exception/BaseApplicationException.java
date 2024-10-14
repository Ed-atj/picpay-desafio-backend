package com.picpay.desafio.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

// This is a structured approach to error handling for HTTP APIs
@RequiredArgsConstructor
public class BaseApplicationException extends RuntimeException {

    public ProblemDetail problemDetail() {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Internal server error.");
        return pb;
    }
    public ProblemDetail problemDetail(String error, String errorCode){
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle(error);
        pb.setDetail(errorCode);

        return pb;
    }

}

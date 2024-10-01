package com.picpay.desafio.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class BaseApplicationException extends RuntimeException {

    public ProblemDetail problemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("Internal server error.");
        return pb;
    }

}

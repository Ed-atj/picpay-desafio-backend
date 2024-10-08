package com.picpay.desafio.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AuthorizationFailedException extends BaseApplicationException{


    @Override
    public ProblemDetail problemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Authorization Failed.");
        pb.setDetail("Authorization wasn't effectuated.");
        return pb;
    }

}

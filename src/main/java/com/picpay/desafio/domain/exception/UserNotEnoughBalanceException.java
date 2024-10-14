package com.picpay.desafio.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserNotEnoughBalanceException extends BaseApplicationException {
    @Override
    public ProblemDetail problemDetail(){
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);


        pb.setTitle("Not enough balance.");
        pb.setDetail("The value of transference is higher than your actual balance.");

        return pb;
    }
}

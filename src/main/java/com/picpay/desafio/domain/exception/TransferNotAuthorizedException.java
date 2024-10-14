package com.picpay.desafio.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends BaseApplicationException{

    @Override
    public ProblemDetail problemDetail() {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Transference Unauthorized");
        pb.setDetail("Transference wasn't allowed by the Authorization service.");

        return pb;
    }
}

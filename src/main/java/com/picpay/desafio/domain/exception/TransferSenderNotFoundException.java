package com.picpay.desafio.domain.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@RequiredArgsConstructor
public class TransferSenderNotFoundException extends BaseApplicationException{

    private final Long senderId;

    @Override
    public ProblemDetail problemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("Transfer sender not found.");
        pb.setDetail("There's no sender with id:" + senderId + ".");
        return pb;
    }
}

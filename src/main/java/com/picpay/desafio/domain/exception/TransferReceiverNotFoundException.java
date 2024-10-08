package com.picpay.desafio.domain.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@RequiredArgsConstructor
public class TransferReceiverNotFoundException extends BaseApplicationException{

    private final Long receiverId;

    @Override
    public ProblemDetail problemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Transfer receiver not found.");
        pb.setDetail("There's no receiver with id: "+ receiverId +".");
        return pb;
    }
}

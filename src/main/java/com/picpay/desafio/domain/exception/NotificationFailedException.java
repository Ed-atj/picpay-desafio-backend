package com.picpay.desafio.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class NotificationFailedException extends BaseApplicationException{

    @Override
    public ProblemDetail problemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("Notification failed.");
        pb.setDetail("Notification wasn't sent.");
        return pb;
    }
}

package com.picpay.desafio.domain.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@RequiredArgsConstructor
public class UserNotFoundException extends BaseApplicationException{

    private final Long userId;

    @Override
    public ProblemDetail problemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("User not found.");
        pb.setDetail("No user with id"+ userId +".");
        return pb;
    }
}

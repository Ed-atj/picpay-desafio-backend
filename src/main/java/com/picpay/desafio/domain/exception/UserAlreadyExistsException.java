package com.picpay.desafio.domain.exception;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@RequiredArgsConstructor
public class UserAlreadyExistsException extends BaseApplicationException{

    private final String message;

    @Override
    public ProblemDetail problemDetail(){
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("User already exists.");
        pb.setDetail(message);
        return pb;
    }

}

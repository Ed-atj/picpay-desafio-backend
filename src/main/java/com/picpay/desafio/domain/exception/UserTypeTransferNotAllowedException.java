package com.picpay.desafio.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class UserTypeTransferNotAllowedException extends BaseApplicationException {

    @Override
    public ProblemDetail problemDetail(){
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);


        pb.setTitle("UserType doesn't have permission.");
        pb.setDetail("Shopkeepers aren't allowed to send values.");

        return pb;
    }
}

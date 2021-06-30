package com.notification.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public final ResponseEntity<Object> handleCustomException(CustomException customException, WebRequest request) {

        return new ResponseEntity(customException, HttpStatus.CONFLICT);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ex.printStackTrace();

        return new ResponseEntity(new CustomException(ErrorCode.ErrorOccurred), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
